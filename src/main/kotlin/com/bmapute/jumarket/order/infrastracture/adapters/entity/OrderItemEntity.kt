package com.bmapute.jumarket.order.infrastracture.adapters.entity

import jakarta.persistence.*
import java.math.BigDecimal


@Entity
@Table(name = "order_item")
data class OrderItemEntity(
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    val productId: Long,
    var quantity:Double,
    var price: BigDecimal,
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
     var id: Long? = null
)
