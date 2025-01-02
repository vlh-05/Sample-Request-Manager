package com.example.ordrsmartapi.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "sample_requests")
data class SampleRequest(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "product_id")
        val product: Product,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "variant_id")
        val variant: Variant,

        val sampleQuantity: String,
        val sampleApplication: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "address_id")
        val shippingAddress: Address,

        val additionalInformation: String? = null,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val createdBy: String
)
