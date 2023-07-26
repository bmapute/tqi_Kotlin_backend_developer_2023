package com.bmapute.jumarket.order.application.web.resource.request

import com.bmapute.jumarket.order.domain.Category
import com.bmapute.jumarket.order.domain.Product
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class ProductRequest(
    @field:NotBlank
    var name: String,
    @field:NotBlank
    val unitOfMeasurement: String,
    @field:Digits(integer = 4, fraction = 2)
    @field:DecimalMin(value = "0.5", inclusive = false)
    val price: BigDecimal,
    @field:Min(1)
    val categoryId: Long
){
    fun toProduct()=Product(name = name,
        unitOfMeasurement = unitOfMeasurement, price = price,
        category = Category(id = categoryId,name=null) )
}
