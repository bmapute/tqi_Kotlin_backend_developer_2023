package com.bmapute.jumarket.order.domain

import com.bmapute.jumarket.order.domain.exception.DomainException
import java.math.BigDecimal
import java.math.MathContext
import java.time.LocalDateTime
import java.util.*


class Order constructor(
    id: UUID,
    orderItems: MutableList<OrderItem>,
    var status: OrderStatus,
    var createAt: LocalDateTime?,
    paymentType: PaymentType,
) {
     val id: UUID
    var orderItems: MutableList<OrderItem>
     var totalAmount: BigDecimal
     val paymentType: PaymentType

    private val m = MathContext(4)

    init {
        this.id = id
        this.orderItems = orderItems
        this.totalAmount =
            orderItems.map { it.price!!.multiply(BigDecimal(it.quantity), m) }
                .fold(BigDecimal.ZERO, BigDecimal::add)
        this.paymentType = paymentType
    }

    fun addOrderItem(product: Product, price: BigDecimal?, quantity: Double) {
        validateState()
        validateProduct(product)
        this.orderItems.add(OrderItem(product.id, quantity, price?:product.price))
        totalAmount = totalAmount.add(price ?: product.price)
    }

    fun removeOrderItem(idProduct: Long) {
        validateState()
        val orderItem: OrderItem = getOrderItem(idProduct)
        orderItems.remove(orderItem)
        totalAmount = totalAmount.subtract(orderItem.price)
    }

    private fun getOrderItem(idProduct: Long) = orderItems.stream().filter {
        it.productId == idProduct
    }.findFirst().orElseThrow { DomainException("Product with $idProduct doesn't exist.") }


    fun complete() {
        validateState()
        this.status = OrderStatus.COMPLETED
    }

    private fun validateState() = if (OrderStatus.COMPLETED == status)
        throw DomainException("The order is in completed state.") else null

    private fun validateProduct(product: Product?) = product ?: throw DomainException("The product cannot be null.")


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        if (status != other.status) return false
        if (id != other.id) return false
        if (orderItems != other.orderItems) return false
        if (totalAmount != other.totalAmount) return false
        if (paymentType != other.paymentType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status?.hashCode() ?: 0
        result = 31 * result + id.hashCode()
        result = 31 * result + orderItems.hashCode()
        result = 31 * result + totalAmount.hashCode()
        result = 31 * result + paymentType.hashCode()
        return result
    }


}