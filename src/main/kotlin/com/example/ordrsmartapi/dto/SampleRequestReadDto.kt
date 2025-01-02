package com.example.ordrsmartapi.dto

import java.time.LocalDateTime

data class SampleRequestReadDto(
        val id: Long,
        val product: ProductDTO,
        val variant: VariantReadDTO,
        val sampleQuantity: String,
        val sampleApplication: String,
        val shippingAddress: AddressDTO,
        val additionalInformation: String?,
        val createdAt: LocalDateTime,
        val createdBy: String
)