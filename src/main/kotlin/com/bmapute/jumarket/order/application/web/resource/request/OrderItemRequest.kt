package com.bmapute.jumarket.order.application.web.resource.request

import com.bmapute.jumarket.order.domain.OrderItem
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.Min
import java.math.BigDecimal

data class OrderItemRequest(
    @field:Min(1)
    val productId: Long,
    @field:DecimalMin(value = "0.1", inclusive = false)
    val quantity: Double,
    @field:Digits(integer = 4, fraction = 2)
    @field:DecimalMin(value = "0.5", inclusive = false)
    val price: BigDecimal
) {

//    companion object {
        fun toOrderItem() = OrderItem(productId, quantity, price)
//    }
}