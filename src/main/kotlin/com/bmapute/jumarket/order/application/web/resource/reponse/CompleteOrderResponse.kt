package com.bmapute.jumarket.order.application.web.resource.reponse

import com.bmapute.jumarket.order.domain.Order
import com.bmapute.jumarket.order.domain.PaymentType
import java.math.BigDecimal
import java.util.UUID

open class CompleteOrderResponse(
    val id: UUID,
    val totalAmount: BigDecimal,
    val paymentType: PaymentType
) {
    companion object {
        fun from(order: Order) =   CompleteOrderResponse(
                order.id,
                order.totalAmount,
                order.paymentType
            )

    }
}