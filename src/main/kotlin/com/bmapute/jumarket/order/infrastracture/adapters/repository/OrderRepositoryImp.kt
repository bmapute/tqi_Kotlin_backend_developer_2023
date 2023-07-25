package com.bmapute.jumarket.order.infrastracture.adapters.repository

import com.bmapute.jumarket.order.domain.Order
import com.bmapute.jumarket.order.domain.repository.OrderRepository
import com.bmapute.jumarket.order.infrastracture.adapters.entity.OrderEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderRepositoryImp(private val jpaRepository: SpringDataJpaOrderRepository) : OrderRepository {
    override fun findById(id: UUID): Optional<Order> {
        val ob = jpaRepository.findById(id)
        return if (jpaRepository.findById(id).isPresent)
            Optional.of(ob.get().toOrder()) else Optional.empty()
    }

    override fun save(order: Order)= jpaRepository.save(OrderEntity.from(order)).toOrder()

}