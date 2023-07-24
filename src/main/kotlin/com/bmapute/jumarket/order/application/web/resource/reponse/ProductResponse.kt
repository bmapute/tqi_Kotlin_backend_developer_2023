package com.bmapute.jumarket.order.application.web.resource.reponse

import com.bmapute.jumarket.order.domain.Product
import java.math.BigDecimal

data class ProductResponse(
    val id: Long?,
    val name: String,
    val unitOfMeasurement: String,
    val price: BigDecimal,
    val categoryId: Long
) {
    companion object {
        fun from(product: Product) = ProductResponse(
            id = product.id,
            name = product.name,
            unitOfMeasurement = product.unitOfMeasurement,
            price = product.price,
            categoryId = product.category.id!!
        )
    }
}
