package com.bmapute.jumarket.order.infrastracture.adapters.repository

import com.bmapute.jumarket.order.domain.Category
import com.bmapute.jumarket.order.domain.repository.CategoryRepository
import com.bmapute.jumarket.order.infrastracture.adapters.entity.CategoryEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class CategoryRepositoryImpl(private val jpaRepository: SpringDataJpaCategoryRepository):CategoryRepository {
    override fun save(category: Category)= jpaRepository.save(CategoryEntity.from(category)).toCategory()
    override fun findAll()= jpaRepository.findAll().map { it.toCategory() }
    override fun update(category: Category)=
        jpaRepository.save(CategoryEntity.from(category)).toCategory()
    override fun delete(id: Long) = jpaRepository.deleteById(id)
    override fun findById(id: Long): Optional<Category> {
        val ob = jpaRepository.findById(id)
        return if (jpaRepository.findById(id).isPresent) Optional.of(ob.get().toCategory())
        else Optional.empty()
    }

}