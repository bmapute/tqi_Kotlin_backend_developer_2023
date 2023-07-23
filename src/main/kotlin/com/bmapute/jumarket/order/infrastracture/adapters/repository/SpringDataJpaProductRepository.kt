package com.bmapute.jumarket.order.infrastracture.adapters.repository

import com.bmapute.jumarket.order.infrastracture.adapters.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataJpaProductRepository:JpaRepository<ProductEntity,Long> {
}