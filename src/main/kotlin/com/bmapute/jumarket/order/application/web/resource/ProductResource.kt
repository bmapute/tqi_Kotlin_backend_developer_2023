package com.bmapute.jumarket.order.application.web.resource

import com.bmapute.jumarket.order.application.web.resource.reponse.ProductResponse
import com.bmapute.jumarket.order.application.web.resource.request.ProductRequest
import com.bmapute.jumarket.order.domain.service.ProductService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping(value = ["${IConstant.API_BASE_PATH}/product"])
class ProductResource(private val service: ProductService) {

    @GetMapping
    fun getProducts() = service.getProducts().map { ProductResponse.from(it) }
        .let { ResponseEntity.ok().body(it) }

    @GetMapping("{id}")
    fun getProduct(@PathVariable("id") id: Long) =
        service.detail(id)?.let {
            ResponseEntity.ok().body(ProductResponse.from(it))
        } ?: ResponseEntity.notFound().build<Void>()

    @PostMapping
    fun createProduct(@Valid @RequestBody request: ProductRequest) =
        request.toProduct().run {
            service.create(this)
        }.let {
            ResponseEntity
                .created(URI("${IConstant.API_BASE_PATH}/${it.id}"))
                .body(ProductResponse.from(it))
        }

    @PutMapping("{id}")
    fun updateProduct(@Valid @RequestBody request: ProductRequest, @PathVariable("id") id: Long) =
        service.detail(id)?.let {
            service.update(id, it)
        }.let { product ->
            ResponseEntity.accepted().body(ProductResponse.from(product!!))
        }

    @DeleteMapping("{id}")
    fun deleteAvenger(@PathVariable("id") id: Long) =
        service.delete(id).let {
            ResponseEntity.accepted().build<Void>()
        }
}