package com.bmapute.jumarket.order.domain.service

import com.bmapute.jumarket.order.domain.OrderItem
import com.bmapute.jumarket.order.domain.OrderStatus
import com.bmapute.jumarket.order.domain.PaymentType
import com.bmapute.jumarket.order.domain.Product
import java.math.BigDecimal
import java.util.*


interface OrderService {

    fun createOrder(orderItems:MutableList<OrderItem>,status: OrderStatus?,paymentType: PaymentType): UUID?
    fun createOrder(product: Product,price:BigDecimal?,quantity:Double,orderStatus: OrderStatus?,paymentType: PaymentType){
        createOrder(mutableListOf(OrderItem(product.id!!,quantity,price?:product.price)),orderStatus,paymentType)
    }

    fun addOrderProduct(id: UUID, product: Product,price: BigDecimal?,quantity: Double)

    fun completeOrder(id: UUID)

    fun deleteProduct(id: UUID, productId: Long)
}