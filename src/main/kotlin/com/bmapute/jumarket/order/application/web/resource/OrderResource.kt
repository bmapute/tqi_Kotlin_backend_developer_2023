package com.bmapute.jumarket.order.application.web.resource

import com.bmapute.jumarket.order.application.web.resource.reponse.CompleteOrderResponse
import com.bmapute.jumarket.order.application.web.resource.reponse.CreateOrderResponse
import com.bmapute.jumarket.order.application.web.resource.request.AddProductToOrderRequest
import com.bmapute.jumarket.order.application.web.resource.request.CreateOrderRequest
import com.bmapute.jumarket.order.domain.service.OrderService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*


@RestController
@RequestMapping(value = ["${IConstant.API_BASE_PATH}/order"])
class OrderResource(private val orderService: OrderService) {
    @PostMapping
    fun createOrder(@Valid @RequestBody createOrderRequest: CreateOrderRequest) =
        orderService.createOrder(
            orderItems = createOrderRequest.toOrderItem(),
            paymentType = createOrderRequest.paymentType
        ).let {
            ResponseEntity.created(URI("${IConstant.API_BASE_PATH}/${it}"))
                .body(CreateOrderResponse.from(it))
        }

    @PostMapping(value = ["/{orderId}/product"])
    fun addProduct(@PathVariable orderId: UUID, @RequestBody request: AddProductToOrderRequest) =
        orderService.addOrderProduct(
            orderId,
            request.productId,
            request.price,
            request.quantity
        ).let {
            ResponseEntity.status(HttpStatus.CREATED).build<Void>()
        }

    @DeleteMapping(value = ["/{orderId}/product"])
    fun deleteProduct(@PathVariable orderId: UUID, @RequestParam productId: Long) =
        orderService.deleteProduct(orderId, productId).let {
            ResponseEntity.ok().build<Void>()
        }

    @PatchMapping("/{orderId}/complete")
    fun completeOrder(@PathVariable orderId: UUID) = orderService.completeOrder(orderId).let {
        ResponseEntity.ok().body(CompleteOrderResponse.from(it))
    }
}