package com.example.ordrsmartapi.dto

data class AddressDTO(
        val line1: String,
        val line2: String?,
        val city: String,
        val state: String,
        val zipCode: String
)

