package com.bmapute.jumarket.order.application.web.resource

import com.bmapute.jumarket.order.application.web.resource.reponse.CategoryResponse
import com.bmapute.jumarket.order.application.web.resource.request.CategoryRequest
import com.bmapute.jumarket.order.domain.service.CategoryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping(value = ["${IConstant.API_BASE_PATH}/category"],
    produces = [MediaType.APPLICATION_JSON_VALUE])
@Tag(name="Category", description = "Endpoints for Managing Category")
class CategoryResource(private val service: CategoryService) {


    @Operation(
        summary = "Get All Category REST API",
        description = "Get All Category REST API is  used to get a all categories from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @GetMapping
    fun getCategories() = service.findAll().map { CategoryResponse.from(it) }
        .let { ResponseEntity.ok().body(it) }


    @Operation(
        summary = "Get Category REST API",
        description = "Get Category REST API is used to get a single category from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @GetMapping("{id}")
    fun getCategory(@PathVariable("id") id: Long) =
        service.find(id)?.let {
            ResponseEntity.ok().body(CategoryResponse.from(it))
        } ?: ResponseEntity.notFound().build<Void>()


    @Operation(
        summary = "Create Category REST API",
        description = "Create Category REST API is used to save category in a database"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 OK"
    )
    @PostMapping
    fun createProduct(@Valid @RequestBody request: CategoryRequest) =
        request.toCategory().run {
            service.create(this)
        }.let {
            ResponseEntity
                .created(URI("${IConstant.API_BASE_PATH}/${it.id}"))
                .body(CategoryResponse.from(it))
        }


    @Operation(
        summary = "Update Category REST API",
        description = "Update Category REST API is used to update a particular category in the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}")
    fun updateProduct(@Valid @RequestBody request: CategoryRequest, @PathVariable("id") id: Long) =
        service.find(id)?.let {
            service.update(id, it)
        }.let { category ->
            ResponseEntity.accepted().body(CategoryResponse.from(category!!))
        }


    @Operation(
        summary = "Delete Category REST API",
        description = "Delete Category REST API is used to delete a particular category from the database"
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
}