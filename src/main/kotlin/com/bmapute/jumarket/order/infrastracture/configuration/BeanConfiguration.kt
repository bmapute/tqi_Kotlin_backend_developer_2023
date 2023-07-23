package com.bmapute.jumarket.order.infrastracture.configuration

import com.bmapute.jumarket.order.domain.repository.OrderRepository
import com.bmapute.jumarket.order.domain.service.OrderDomainService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration {
    @Bean
    fun orderService(repository: OrderRepository): OrderDomainService {
        return OrderDomainService(repository)
    }


}