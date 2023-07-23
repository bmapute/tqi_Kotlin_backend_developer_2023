package com.bmapute.jumarket.order.domain.service

import com.bmapute.jumarket.order.domain.Product
import com.bmapute.jumarket.order.domain.repository.ProductRepository

class ProductDomainService(private val productRepository: ProductRepository) : ProductService {
    override fun create(product: Product): Product {
        return productRepository.save(product)
    }

    override fun detail(id: Long) = productRepository
        .findById(id)?.orElseThrow { RuntimeException("Order with given id doesn't exist") }

    override fun update(id: Long, product: Product) = product.apply {
        this.id = id
    }.also { productRepository.update(it) }

    override fun delete(id: Long) = productRepository.delete(id)
}