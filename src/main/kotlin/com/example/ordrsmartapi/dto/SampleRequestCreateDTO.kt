package com.example.ordrsmartapi.dto

data class SampleRequestCreateDTO (
        var product_id: Long,
        var variant_id: Long,
        var sample_quantity: String,
        var sample_application: String,
        var shipping_address: AddressCreateDTO,
        var additional_information: String,
        var requestor: String
)

