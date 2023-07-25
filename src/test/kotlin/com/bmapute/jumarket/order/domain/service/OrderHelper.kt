package com.bmapute.jumarket.order.domain.service

import com.bmapute.jumarket.order.domain.Order
import com.bmapute.jumarket.order.domain.OrderItem
import com.bmapute.jumarket.order.domain.OrderStatus
import com.bmapute.jumarket.order.domain.PaymentType
import java.math.BigDecimal
import java.util.*

class OrderHelper {
    companion object{
         fun completedOrder() = Order(
            id = UUID.randomUUID(),
            orderItems = mutableListOf(OrderItem(1L, 4.0, BigDecimal.TEN)),
            status = OrderStatus.COMPLETED,
            null,
            paymentType = PaymentType.CASH
        )

         fun createdOrder() = Order(
            id = UUID.randomUUID(),
            orderItems = mutableListOf(OrderItem(1L, 4.0, BigDecimal.TEN)),
            status = OrderStatus.CREATED,
            null,
            paymentType = PaymentType.CASH
        )
    }
}