package com.bmapute.jumarket.order.application.web.resource

import com.bmapute.jumarket.order.application.web.resource.reponse.CompleteOrderResponse
import com.bmapute.jumarket.order.application.web.resource.reponse.CreateOrderResponse
import com.bmapute.jumarket.order.application.web.resource.request.AddProductToOrderRequest
import com.bmapute.jumarket.order.application.web.resource.request.CreateOrderRequest
import com.bmapute.jumarket.order.domain.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*


@RestController
@RequestMapping(
    value = ["${IConstant.API_BASE_PATH}/order"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Order", description = "Endpoints for Managing Order")
class OrderResource(private val orderService: OrderService) {

    @Operation(
        summary = "Create Order REST API",
        description = "Create Order REST API is used to save order in a database"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 OK"
    )
    @PostMapping
    fun createOrder(@Valid @RequestBody createOrderRequest: CreateOrderRequest) =
        orderService.createOrder(
            orderItems = createOrderRequest.toOrderItem(),
            paymentType = createOrderRequest.paymentType
        ).also {
            ResponseEntity.created(URI("${IConstant.API_BASE_PATH}/${it}"))
                .body(CreateOrderResponse.from(it))
        }

    @Operation(
        summary = "Add Product REST API",
        description = "Add Product REST API is used to add order item to existing order"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 OK"
    )
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


    @Operation(
        summary = "Delete Product REST API",
        description = "Delete Product REST API is used to remove order item associated to specific order"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 OK"
    )
    @DeleteMapping(value = ["/{orderId}/product"])
    fun deleteProduct(@PathVariable orderId: UUID, @RequestParam productId: Long) =
        orderService.deleteProduct(orderId, productId).let {
            ResponseEntity.ok().build<Void>()
        }

    @Operation(
        summary = "Complete Order REST API",
        description = "Complete Order REST API is used to finalize order, after this operation doesn't possible" +
                "add or remove item"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 OK"
    )
    @PatchMapping("/{orderId}/complete")
    fun completeOrder(@PathVariable orderId: UUID) = orderService.completeOrder(orderId).also {
        ResponseEntity.ok().body(CompleteOrderResponse.from(it))
    }

    @Operation(
        summary = "Order Detail REST API",
        description = "Is used to see details for specif order"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @GetMapping("/{orderId}/detail")
    fun getOrderDetails(@PathVariable orderId: UUID) = orderService.orderDetail(orderId).also {
        ResponseEntity.ok().body(CompleteOrderResponse.from(it))
    }
}