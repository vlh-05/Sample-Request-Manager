package com.example.ordrsmartapi.utils.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun generalExceptionHandler(exception: Exception): ResponseEntity<ApiError>{
        val error = ApiError(exception.message)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(ProductException::class)
    fun productExceptionHandler(exception: ProductException): ResponseEntity<ApiError>{
        val error = ApiError(message = exception.message, status = HttpStatus.NOT_FOUND)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(AddressException::class)
    fun addressExceptionHandler(exception: AddressException): ResponseEntity<ApiError>{
        val error = ApiError(message = exception.message, status = HttpStatus.NOT_FOUND)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(SampleRequestException::class)
    fun sampleRequestExceptionHandler(exception: SampleRequestException): ResponseEntity<ApiError>{
        val error = ApiError(message = exception.message, status =  HttpStatus.NOT_FOUND)
        return ResponseEntity(error, error.status)
    }
}