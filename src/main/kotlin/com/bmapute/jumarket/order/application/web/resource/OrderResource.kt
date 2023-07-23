package com.bmapute.jumarket.order.application.web.resource

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["${IConstant.API_BASE_PATH}/order"])
class OrderResource {
}