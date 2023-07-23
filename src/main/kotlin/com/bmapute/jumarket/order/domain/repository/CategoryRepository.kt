package com.bmapute.jumarket.order.domain.repository

import com.bmapute.jumarket.order.domain.Category
import com.bmapute.jumarket.order.domain.Product

interface CategoryRepository {

    fun save(category: Category): Category
    fun findAll():List<Category>
    fun update(id:Long,product: Product): Product
    fun delete(id:Long)
}