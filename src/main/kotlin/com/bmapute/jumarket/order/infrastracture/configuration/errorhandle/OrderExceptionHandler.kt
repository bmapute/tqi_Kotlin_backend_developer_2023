package com.bmapute.jumarket.order.infrastracture.configuration.errorhandle

import com.bmapute.jumarket.order.domain.exception.DomainException
import org.springframework.context.support.DefaultMessageSourceResolvable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*
import java.util.stream.Collectors

@ControllerAdvice
class OrderExceptionHandler {

    @ExceptionHandler(DomainException::class)
    fun handlerDomainException(ex: DomainException) =
        ErrorMessage(
            700,
            Date(), ex.message
        )
            .let { ResponseEntity.status(700).body(it) }

    @ExceptionHandler(Exception::class)
    fun handlerException(ex: Exception) =
        ErrorMessage(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            Date(), ex.message
        )
            .let { ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(it) }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValid(ex: MethodArgumentNotValidException) {
        val errors = ex.bindingResult
            .fieldErrors
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList()).joinToString()
        val message = ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            Date(), errors
        )
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message)
    }
}
