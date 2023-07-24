package com.bmapute.jumarket.order.domain.repository

import com.bmapute.jumarket.order.domain.Category
import java.util.*

interface CategoryRepository {

    fun save(category: Category): Category
    fun findAll():List<Category>
    fun update(category: Category): Category
    fun delete(id:Long)
    fun findById(id: Long): Optional<Category>
}