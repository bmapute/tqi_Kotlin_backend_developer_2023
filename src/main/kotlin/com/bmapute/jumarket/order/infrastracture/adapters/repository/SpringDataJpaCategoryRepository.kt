package com.bmapute.jumarket.order.infrastracture.adapters.repository

import com.bmapute.jumarket.order.infrastracture.adapters.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataJpaCategoryRepository:JpaRepository<CategoryEntity,Long> {
}