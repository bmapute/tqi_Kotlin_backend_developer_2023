package com.bmapute.jumarket.order.application.web.resource.request


import java.math.BigDecimal

class PartialUpadateContract {
    class PartialProductRequest {
        var name: String? = null
        var unitOfMeasurement: String? = null
        var price: BigDecimal? = null
        var categoryId: Long? = null
    }

    class PartialUpdateResponse {
        var name: String? = null
        var unitOfMeasurement: String? = null
        var price: BigDecimal? = null
        var categoryId: Long? = null
    }
}