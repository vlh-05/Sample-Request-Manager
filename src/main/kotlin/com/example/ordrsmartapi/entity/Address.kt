package com.example.ordrsmartapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "addresses")
data class Address(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val line1: String,
        val line2: String?,
        val city: String,
        val state: String,
        val zipCode: String
)