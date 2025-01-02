package com.example.ordrsmartapi.dto

data class ResponseOfSampleRequestDto(
        val success: Boolean,
        val message: String,
        val sampleRequest: SampleRequestReadDto,
)