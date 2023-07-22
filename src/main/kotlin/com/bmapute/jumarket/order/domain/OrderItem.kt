package com.bmapute.jumarket.order.domain

import java.math.BigDecimal

data class OrderItem( val productId: Long,
                      var quantity:Double,
                      var price: BigDecimal?
)
