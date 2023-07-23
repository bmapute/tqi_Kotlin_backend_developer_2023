package com.bmapute.jumarket.order.infrastracture.adapters.entity

import com.bmapute.jumarket.order.domain.Category
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "category")
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String
)
{
    fun toCategory()=Category(id!!,name)
    companion object{
        fun from(category: Category): CategoryEntity {
          return CategoryEntity(category.id,category.name)
        }
    }

}