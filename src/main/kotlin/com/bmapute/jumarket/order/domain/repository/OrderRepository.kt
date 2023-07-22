package com.bmapute.jumarket.order.domain.repository

import com.bmapute.jumarket.order.domain.Order
import java.util.*

interface OrderRepository {
    fun findById(id: UUID?): Optional<Order?>?
    fun save(order: Order?):Order
}