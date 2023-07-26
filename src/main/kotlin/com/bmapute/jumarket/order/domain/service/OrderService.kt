package com.bmapute.jumarket.order.domain.service

import com.bmapute.jumarket.order.domain.Order
import com.bmapute.jumarket.order.domain.OrderItem
import com.bmapute.jumarket.order.domain.OrderStatus
import com.bmapute.jumarket.order.domain.PaymentType
import java.math.BigDecimal
import java.util.*


interface OrderService {

    fun createOrder(orderItems:MutableSet<OrderItem>,
                    status: OrderStatus?=OrderStatus.CREATED,
                    paymentType: PaymentType):Order
    fun addOrderProduct(orderId: UUID, productId: Long,price: BigDecimal?,quantity: Double)

    fun completeOrder(id: UUID):Order

    fun deleteProduct(id: UUID, productId: Long)

    fun orderDetail(id:UUID):Order
}