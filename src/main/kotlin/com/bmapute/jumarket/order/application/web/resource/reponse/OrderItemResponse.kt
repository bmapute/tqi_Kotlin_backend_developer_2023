package com.bmapute.jumarket.order.application.web.resource.reponse

import java.math.BigDecimal

data class OrderItemResponse(
    val productId: Long,
    val quantity: Double,
    val price: BigDecimal
)
