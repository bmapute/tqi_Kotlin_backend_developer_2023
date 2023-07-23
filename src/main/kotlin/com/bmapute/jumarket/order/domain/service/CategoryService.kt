package com.bmapute.jumarket.order.domain.service

import com.bmapute.jumarket.order.domain.Category
import com.bmapute.jumarket.order.domain.Product

interface CategoryService {
    fun create(category: Category): Category
    fun findAll():List<Category>
    fun update(id:Long,product: Product): Product
    fun delete(id:Long)
}