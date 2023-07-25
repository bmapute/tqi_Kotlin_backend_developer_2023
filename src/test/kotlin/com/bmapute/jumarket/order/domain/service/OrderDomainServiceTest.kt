package com.bmapute.jumarket.order.domain.service

import com.bmapute.jumarket.order.domain.OrderItem
import com.bmapute.jumarket.order.domain.OrderStatus
import com.bmapute.jumarket.order.domain.PaymentType
import com.bmapute.jumarket.order.domain.exception.DomainException
import com.bmapute.jumarket.order.domain.repository.OrderRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.kotlin.any
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import java.math.BigDecimal
import java.util.*


class OrderDomainServiceTest {

    @Mock
    lateinit var repository: OrderRepository

    @Mock
    lateinit var productService: ProductService

    @InjectMocks
    lateinit var target: OrderDomainService

    @BeforeEach
    fun init() {
        openMocks(this)
    }

    @Test
    fun `should create order and save it`() {
        val order = target.createOrder(
            mutableListOf(OrderItem(1, 4.0, BigDecimal.TEN)),
            null, PaymentType.CASH
        )

        verify(repository).save(any())
        verify(repository, times(1)).save(any())
        assertNotNull(order)
        assertEquals(OrderStatus.CREATED, order.status)
    }

    @Test
    fun `add order item when order is found and save`() {
        val order = spy(OrderHelper.createdOrder())
        `when`(repository.findById(order.id)).thenReturn(Optional.of(order))
        target.addOrderProduct(order.id, 2, BigDecimal.ONE, 3.0)
        verify(order).addOrderItem(any(), any(), any());
        verify(repository).save(order);
    }

    @Test
    fun `throw exception when order is not found while trying add order item`() {
        val order = spy(OrderHelper.createdOrder())
        `when`(repository.findById(order.id)).thenReturn(Optional.of(order))

        assertThrows(
            DomainException::class.java
        ) {
            target.addOrderProduct(UUID.randomUUID(), 2, BigDecimal.ONE, 3.0)
        }
    }

    @Test
    fun `test complete order`() {
        val order = spy(OrderHelper.createdOrder())
        `when`(repository.findById(order.id)).thenReturn(Optional.of(order))
        target.completeOrder(order.id)
        verify(order).complete();
        verify(repository).save(order);
    }

    @Test
    fun `test remove orderitem on order`() {
        val order = spy(OrderHelper.createdOrder())
        val productId = order
            .orderItems
            .get(0)
            .productId

        `when`(repository.findById(order.id)).thenReturn(Optional.of(order))

        target.deleteProduct(order.id, productId)

        verify(repository).save(order)
        verify(order).removeOrderItem(productId)
    }

    @Test
    fun `test remove item on completed order throw exception`() {
        val order = spy(OrderHelper.completedOrder())
        val productId = order
            .orderItems
            .get(0)
            .productId

        `when`(repository.findById(order.id)).thenReturn(Optional.of(order))

        assertThrows(
            DomainException::class.java
        ) { target.deleteProduct(order.id, productId) }

    }
}

