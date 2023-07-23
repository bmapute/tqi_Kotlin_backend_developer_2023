package com.bmapute.jumarket.order.infrastracture.adapters.repository

import com.bmapute.jumarket.order.infrastracture.adapters.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SpringDataJpaOrderRepository:JpaRepository<OrderEntity,UUID> {
}