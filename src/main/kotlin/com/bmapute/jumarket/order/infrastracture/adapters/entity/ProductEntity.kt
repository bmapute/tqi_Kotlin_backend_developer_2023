package com.bmapute.jumarket.order.infrastracture.adapters.entity

import com.bmapute.jumarket.order.domain.Product
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "product")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val unitOfMeasurement: String,

    @Column(nullable = false)
    val price: BigDecimal,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    val category: CategoryEntity
) {
    fun toProduct()=Product(id,name,unitOfMeasurement,price, category.toCategory())

    companion object{
        fun from(product: Product)= ProductEntity(product.id,product.name,
            product.unitOfMeasurement,product.price, CategoryEntity.from(product.category))
    }
}
