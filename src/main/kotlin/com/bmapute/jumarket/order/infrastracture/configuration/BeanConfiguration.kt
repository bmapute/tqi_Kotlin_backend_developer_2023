package com.bmapute.jumarket.order.infrastracture.configuration

import com.bmapute.jumarket.order.domain.repository.CategoryRepository
import com.bmapute.jumarket.order.domain.repository.OrderRepository
import com.bmapute.jumarket.order.domain.repository.ProductRepository
import com.bmapute.jumarket.order.domain.service.CategoryDomainService
import com.bmapute.jumarket.order.domain.service.OrderDomainService
import com.bmapute.jumarket.order.domain.service.ProductDomainService
import com.bmapute.jumarket.order.domain.service.ProductService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration {
    @Bean
    fun ProductService(repository: ProductRepository)= ProductDomainService(repository)
    @Bean
    fun orderService(repository: OrderRepository, productService: ProductService)=
        OrderDomainService(repository,productService)

    @Bean
    fun categoryService(repository: CategoryRepository)= CategoryDomainService(repository)



}