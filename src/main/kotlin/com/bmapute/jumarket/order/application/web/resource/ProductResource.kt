package com.bmapute.jumarket.order.application.web.resource

import com.bmapute.jumarket.order.application.web.resource.reponse.ProductResponse
import com.bmapute.jumarket.order.application.web.resource.request.PartialUpadateContract
import com.bmapute.jumarket.order.application.web.resource.request.ProductRequest
import com.bmapute.jumarket.order.domain.Product
import com.bmapute.jumarket.order.domain.service.ProductService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.ReflectionUtils
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping(
    value = ["${IConstant.API_BASE_PATH}/product"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Product", description = "Endpoints for Managing Product")
class ProductResource(private val service: ProductService) {

    @Operation(
        summary = "Get All Product REST API",
        description = "Get All Product REST API is  used to get a all the products from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @GetMapping
    fun getProducts() = service.getProducts().map { ProductResponse.from(it) }
        .let { ResponseEntity.ok().body(it) }


    @Operation(
        summary = "Get Product REST API",
        description = "Get Product REST API is used to get a single product from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @GetMapping("{id}")
    fun getProduct(@PathVariable("id") id: Long) =
        service.detail(id)?.let {
            ResponseEntity.ok().body(ProductResponse.from(it))
        } ?: ResponseEntity.notFound().build<Void>()


    @Operation(
        summary = "Create Product REST API",
        description = "Create Product REST API is used to save product in a database"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 OK"
    )
    @PostMapping
    fun createProduct(@Valid @RequestBody request: ProductRequest) =
        request.toProduct().run {
            service.create(this)
        }.let {
            ResponseEntity
                .created(URI("${IConstant.API_BASE_PATH}/${it.id}"))
                .body(ProductResponse.from(it))
        }

    @Operation(
        summary = "Update Product REST API",
        description = "Update Product REST API is used to update a particular product in the database"
    )
    @ApiResponse(
        responseCode = "202",
        description = "HTTP Status 202 SUCCESS"
    )
    @PutMapping("{id}")
    fun updateProduct(@Valid @RequestBody request: ProductRequest, @PathVariable("id") id: Long) =
        service.detail(id)?.let {
            service.update(it.id!!, request.toProduct())
        }.also { product ->
            ResponseEntity.accepted().body(ProductResponse.from(product!!))
        }


    @Operation(
        summary = "Partial Update Product REST API",
        description = "Partial Update REST API is used to update a particular product field in the database"
    )
    @ApiResponse(
        responseCode = "202",
        description = "HTTP Status 202 SUCCESS"
    )
    @PatchMapping("{id}")
    fun partialUpdateForProduct(@PathVariable("id") id: Long, @RequestBody fields: Map<String, Object>): Product? {
        val newValues = merge(fields)
        val oldValue = service.detail(id)!!
        val request = ProductRequest(
            name = newValues.name ?: oldValue.name,
            unitOfMeasurement = newValues.unitOfMeasurement ?: oldValue.unitOfMeasurement,
            price = newValues.price ?: oldValue.price,
            categoryId = newValues.categoryId ?: oldValue.category.id!!
        )
        return updateProduct(request, id)
    }

    @Operation(
        summary = "Delete Product REST API",
        description = "Delete Product REST API is used to delete a particular product from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    fun deleteAvenger(@PathVariable("id") id: Long) =
        service.delete(id).let {
            ResponseEntity.accepted().build<Void>()
        }

    private fun merge(filledValues: Map<String, Object>): PartialUpadateContract.PartialUpdateResponse {
        val response = PartialUpadateContract.PartialUpdateResponse()
        val mapper = ObjectMapper().apply { propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE }
        val partialRequestWithValues =
            mapper.convertValue(filledValues, PartialUpadateContract.PartialProductRequest::class.java)

        filledValues.forEach {
            val from = ReflectionUtils.findField(
                PartialUpadateContract.PartialProductRequest::class.java,
                it.key.snakeToCamelCase()
            )
            from!!.isAccessible = true
            val clientValue = ReflectionUtils.getField(from, partialRequestWithValues)
            val to = ReflectionUtils.findField(
                PartialUpadateContract.PartialUpdateResponse::class.java,
                it.key.snakeToCamelCase()
            )
            to!!.isAccessible = true
            ReflectionUtils.setField(to!!, response, clientValue)
        }

        return response;
    }

    fun String.snakeToCamelCase(): String {
        val pattern = "_[a-z]".toRegex()
        return replace(pattern) { it.value.last().uppercase() }
    }
}