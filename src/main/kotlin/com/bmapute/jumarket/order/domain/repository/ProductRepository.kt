package com.bmapute.jumarket.order.domain.repository

import com.bmapute.jumarket.order.domain.Product
import java.util.*

interface ProductRepository {

    fun findById(id: Long): Optional<Product>
    fun save(product: Product): Product

    fun update(product: Product):Product

    fun delete(id:Long)
    fun findAll(): List<Product>
}