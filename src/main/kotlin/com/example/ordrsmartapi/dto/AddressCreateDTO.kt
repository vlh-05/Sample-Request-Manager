package com.example.ordrsmartapi.dto

data class AddressCreateDTO(
        val line_1: String,
        val line_2: String?,
        val city: String,
        val state: String,
        val zip_code: String
)