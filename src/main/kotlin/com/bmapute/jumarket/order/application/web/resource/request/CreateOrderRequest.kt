package com.bmapute.jumarket.order.application.web.resource.request

import com.bmapute.jumarket.order.domain.PaymentType
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull

data class CreateOrderRequest(
    @field:Valid
    val orderItems: MutableList<OrderItemRequest>,
    @field:NotNull
    val paymentType: PaymentType
) {
    fun toOrderItem()=orderItems.mapTo(mutableSetOf()){it.toOrderItem()}

}