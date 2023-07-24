package com.bmapute.jumarket.order.application.web.resource

import com.bmapute.jumarket.order.application.web.resource.reponse.CategoryResponse
import com.bmapute.jumarket.order.application.web.resource.request.CategoryRequest
import com.bmapute.jumarket.order.domain.service.CategoryService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping(value = ["${IConstant.API_BASE_PATH}/category"])
class CategoryResource(private val service: CategoryService) {

    @GetMapping
    fun getCategories() = service.findAll().map { CategoryResponse.from(it) }
        .let { ResponseEntity.ok().body(it) }

    @GetMapping("{id}")
    fun getCategory(@PathVariable("id") id: Long) =
        service.find(id)?.let {
            ResponseEntity.ok().body(CategoryResponse.from(it))
        } ?: ResponseEntity.notFound().build<Void>()

    @PostMapping
    fun createProduct(@Valid @RequestBody request: CategoryRequest) =
        request.toCategory().run {
            service.create(this)
        }.let {
            ResponseEntity
                .created(URI("${IConstant.API_BASE_PATH}/${it.id}"))
                .body(CategoryResponse.from(it))
        }

    @PutMapping("{id}")
    fun updateProduct(@Valid @RequestBody request: CategoryRequest, @PathVariable("id") id: Long) =
        service.find(id)?.let {
            service.update(id, it)
        }.let { category ->
            ResponseEntity.accepted().body(CategoryResponse.from(category!!))
        }

    @DeleteMapping("{id}")
    fun deleteAvenger(@PathVariable("id") id: Long) =
        service.delete(id).let {
            ResponseEntity.accepted().build<Void>()
        }
}