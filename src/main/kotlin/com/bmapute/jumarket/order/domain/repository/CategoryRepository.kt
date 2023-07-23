package com.bmapute.jumarket.order.domain.repository

import com.bmapute.jumarket.order.domain.Category

interface CategoryRepository {

    fun save(category: Category): Category
    fun findAll():List<Category>
    fun update(category: Category): Category
    fun delete(id:Long)
}