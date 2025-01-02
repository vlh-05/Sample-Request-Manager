package com.example.ordrsmartapi.utils.exception

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class ApiError(
        val message: String? = "An error occurred",
        val status: HttpStatus = HttpStatus.BAD_REQUEST,
        val code: Int = status.value(),
        val timestamp: LocalDateTime = LocalDateTime.now()
) {

}
