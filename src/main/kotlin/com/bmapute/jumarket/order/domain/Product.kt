package com.bmapute.jumarket.order.domain

import java.math.BigDecimal

data class Product(
    var id:Long?,
    val name:String,
    val unitOfMeasurement:String,
    val price:BigDecimal,
    val category: Category
) {
}