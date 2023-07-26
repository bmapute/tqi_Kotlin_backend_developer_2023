package com.bmapute.jumarket.order.application.web.resource.request

import com.bmapute.jumarket.order.domain.Category
import jakarta.validation.constraints.NotBlank

data class CategoryRequest(
    @field:NotBlank
    val name: String?
) {
    fun toCategory() = Category( null, name = name)
}
