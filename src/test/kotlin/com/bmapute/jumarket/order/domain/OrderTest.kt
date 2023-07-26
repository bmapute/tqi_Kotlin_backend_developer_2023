package com.bmapute.jumarket.order.domain

import com.bmapute.jumarket.order.domain.exception.DomainException
import com.bmapute.jumarket.order.domain.service.OrderHelper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*

class OrderTest {
    @Test
    fun `should add product then update price`(){
        val order =OrderHelper.createdOrder()
        val orderInitialSize = order.orderItems.size
        val orderInitialAmount = order.totalAmount
        val newProductId = 2L
        val newProductPrice = BigDecimal.valueOf(8)
        val newProductQuantity = 3.0
        val newProductTotalAmout=newProductPrice.multiply(BigDecimal(newProductQuantity))
        order.addOrderItem(newProductId, newProductPrice, newProductQuantity)
        assertEquals(orderInitialSize + 1, order.orderItems.size)
        assertEquals(orderInitialAmount.add(newProductTotalAmout), order.totalAmount)

    }

    @Test
    fun `should add product to order in completeStatus then throw exception`(){
        val order = OrderHelper.completedOrder()
        val newProductId = 2L
        val newProductPrice = BigDecimal.valueOf(8)
        val newProductQuantity = 3.0

        assertThrows(
            DomainException::class.java
        ) { order.addOrderItem(newProductId,newProductPrice,newProductQuantity) }
    }

    @Test
    fun `should remove product then update price`() {
        val order =OrderHelper.createdOrder()
        val productId=order.orderItems.iterator().next().productId
        order.removeOrderItem(productId)
        assertEquals(BigDecimal.ZERO,order.totalAmount)
    }

    @Test
    fun `should complete order then change status`() {
        val order = OrderHelper.createdOrder()
        order.complete()
        assertEquals(OrderStatus.COMPLETED,order.status)
    }


}