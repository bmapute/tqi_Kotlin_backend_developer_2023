package com.bmapute.jumarket.order.infrastracture.adapters.repository

import com.bmapute.jumarket.order.domain.Product
import com.bmapute.jumarket.order.domain.repository.ProductRepository
import com.bmapute.jumarket.order.infrastracture.adapters.entity.ProductEntity
import org.springframework.stereotype.Component
import java.util.*
@Component
class ProductRepositoryImpl(private val jpaRepository: SpringDataJpaProductRepository):ProductRepository {
    override fun findById(id: Long): Optional<Product> {
        val ob = jpaRepository.findById(id)
        return if (jpaRepository.findById(id).isPresent) Optional.of(ob.get().toProduct()) else Optional.empty()
    }

    override fun save(product: Product)=jpaRepository.save(ProductEntity.from(product)).toProduct()

    override fun update(product: Product)=jpaRepository.save(ProductEntity.from(product)).toProduct()

    override fun delete(id: Long)=jpaRepository.deleteById(id)
}