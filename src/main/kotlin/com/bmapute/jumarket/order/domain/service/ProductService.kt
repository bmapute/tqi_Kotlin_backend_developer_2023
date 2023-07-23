package com.bmapute.jumarket.order.domain.service

import com.bmapute.jumarket.order.domain.Product

interface ProductService {
    fun create(product: Product):Product
    fun detail(id:Long):Product?
    fun update(id:Long,product: Product):Product
    fun delete(id:Long)
}