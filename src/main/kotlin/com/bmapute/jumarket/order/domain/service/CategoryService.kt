package com.bmapute.jumarket.order.domain.service

import com.bmapute.jumarket.order.domain.Category

interface CategoryService {
    fun create(category: Category): Category
    fun findAll():List<Category>
    fun update(id:Long,category: Category): Category
    fun delete(id:Long)
    fun find(id:Long):Category?
}