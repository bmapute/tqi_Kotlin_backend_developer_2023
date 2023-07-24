package com.bmapute.jumarket.order.domain.service

import com.bmapute.jumarket.order.domain.Category
import com.bmapute.jumarket.order.domain.repository.CategoryRepository

class CategoryDomainService(private val repository: CategoryRepository) : CategoryService {
    override fun create(category: Category) = repository.save(category)
    override fun findAll(): List<Category> = repository.findAll()
    override fun update(id: Long, category: Category) = category.apply {
        this.id = id
        this.name = category.name
    }.also { repository.update(it) }

    override fun delete(id: Long) = repository.delete(id)
    override fun find(id: Long): Category? =
        repository.findById(id)?.orElseThrow { RuntimeException("Category with given id doesn't exist") }
}