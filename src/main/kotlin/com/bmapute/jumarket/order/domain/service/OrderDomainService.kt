package com.bmapute.jumarket.order.domain.service

import com.bmapute.jumarket.order.domain.*
import com.bmapute.jumarket.order.domain.repository.OrderRepository
import java.math.BigDecimal
import java.util.*

class OrderDomainService(private val repository: OrderRepository) : OrderService {
    override fun createOrder(orderItems: MutableList<OrderItem>, orderStatus: OrderStatus?, paymentType: PaymentType) =
        Order(UUID.randomUUID(), orderItems, orderStatus ?: OrderStatus.CREATED, null, paymentType).let {
            repository.save(it)
        }.id

    override fun addOrderProduct(id: UUID, product: Product, price: BigDecimal?, quantity: Double) {
        getOrder(id)?.apply {
            addOrderItem(product, price ?: product.price, quantity)
        }.let { repository.save(it!!) }
    }

    override fun completeOrder(id: UUID) {
        getOrder(id)?.apply { complete()}.let { repository.save(it!!) }
    }

    override fun deleteProduct(id: UUID, productId: Long) {
        getOrder(id)?.apply { removeOrderItem(productId) }.let { repository.save(it!!) }
    }

    private fun getOrder(id: UUID) =
        repository
            .findById(id)?.orElseThrow { RuntimeException("Order with given id doesn't exist") }
}