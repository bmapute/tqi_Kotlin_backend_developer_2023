package com.bmapute.jumarket.order.infrastracture.adapters.entity

import com.bmapute.jumarket.order.domain.Order
import com.bmapute.jumarket.order.domain.OrderItem
import com.bmapute.jumarket.order.domain.OrderStatus
import com.bmapute.jumarket.order.domain.PaymentType
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
//@Table(name = "order")
data class OrderEntity(
    @Id
    @Column(name = "id")
    val id: UUID,
    @OneToMany(
        fetch = FetchType.EAGER, cascade = [CascadeType.ALL],
        targetEntity = OrderItemEntity::class
    )
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    val orderItems: MutableSet<OrderItemEntity>,
    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    val status: OrderStatus,
    @Column(name = "create_at")
    val createAt: LocalDateTime? = LocalDateTime.now(),
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val paymentType: PaymentType,
    @Column(nullable = false)
    val totalAmount: BigDecimal
) {
    fun toOrder(): Order {
        val convertedItems = orderItems
            .mapTo(mutableSetOf()) {
                OrderItem(
                    it.productId,
                    it.quantity, it.price
                )
            }
        return Order(id, convertedItems, status, createAt, paymentType)
    }

    companion object {
        fun from(order: Order): OrderEntity {
            val convertedItems = order.orderItems
                .mapTo(mutableSetOf()) {
                    OrderItemEntity(
                        it.productId,
                        it.quantity, it.price
                    )
                }

            return OrderEntity(
                id = order.id, orderItems = convertedItems,
                status = order.status, createAt = order.createAt,
                paymentType = order.paymentType, totalAmount = order.totalAmount
            )
        }
    }
}
