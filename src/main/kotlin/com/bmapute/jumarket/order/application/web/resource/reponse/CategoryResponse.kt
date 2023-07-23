package com.bmapute.jumarket.order.application.web.resource.reponse

import com.bmapute.jumarket.order.domain.Category

data class CategoryResponse(val id: Long?,
                       val name: String?) {
    companion object {
        fun from(category: Category) = CategoryResponse(
            id = category.id,
            name = category.name,
        )
    }
}