package com.bmapute.jumarket.order.domain.service

import com.bmapute.jumarket.order.domain.Category
import com.bmapute.jumarket.order.domain.Product
import com.bmapute.jumarket.order.domain.repository.CategoryRepository

class CategoryDomainService(private val repository: CategoryRepository) : CategoryService {
    override fun create(category: Category) = repository.save(category)
    override fun findAll(): List<Category> = repository.findAll()
    override fun update(id: Long, product: Product): Product = repository.update(id, product)
    override fun delete(id: Long) = repository.delete(id)
}