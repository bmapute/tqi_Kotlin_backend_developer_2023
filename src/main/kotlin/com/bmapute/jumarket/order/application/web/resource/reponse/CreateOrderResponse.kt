package com.bmapute.jumarket.order.application.web.resource.reponse

import com.bmapute.jumarket.order.domain.Order
import com.bmapute.jumarket.order.domain.PaymentType
import java.math.BigDecimal
import java.util.*

 class CreateOrderResponse(
     val items:List<OrderItemResponse>,
      id: UUID,
     totalAmount: BigDecimal,
     paymentType: PaymentType):
    CompleteOrderResponse(id, totalAmount, paymentType) {

     companion object {
         fun from(order: Order) {
             val items = order.orderItems.map { OrderItemResponse(it.productId, it.quantity, it.price) }
             CreateOrderResponse(
                 items,
                 order.id,
                 order.totalAmount,
                 order.paymentType,
             )
         }
     }
}