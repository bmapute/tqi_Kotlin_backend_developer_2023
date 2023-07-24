package com.bmapute.jumarket.order.infrastracture.configuration.errorhandle

import java.util.Date

data class ErrorMessage(
    val statusCode: Int,
    val timestamp: Date,
    val message: String?,
)
