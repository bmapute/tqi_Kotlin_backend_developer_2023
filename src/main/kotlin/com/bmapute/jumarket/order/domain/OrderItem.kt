package com.bmapute.jumarket.order.domain

import java.math.BigDecimal

data class OrderItem( val productId: Long,
                      var quantity:Double,
                      var price: BigDecimal


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderItem

        if (productId != other.productId) return false
        if (quantity != other.quantity) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = productId.hashCode()
        result = 31 * result + quantity.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }
}
