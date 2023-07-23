package com.bmapute.jumarket.order.application.web.resource.request

import com.bmapute.jumarket.order.domain.Category
import jakarta.validation.constraints.NotBlank

data class CategoryRequest(
    val id: Long? = null,
    @field:NotBlank
   private val name: String?
) {
    fun toCategory() = Category(id = id ?: null, name = name)
}
