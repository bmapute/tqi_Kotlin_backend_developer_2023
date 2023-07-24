package com.bmapute.jumarket.order.application.web.resource.request

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import java.math.BigDecimal
import java.util.*

data class AddProductToOrderRequest(
    @field:Min(1)
    val productId: Long,
    val price: BigDecimal?=null,
    @field:DecimalMin(value = "0.1", inclusive = false)
    val quantity: Double
)
