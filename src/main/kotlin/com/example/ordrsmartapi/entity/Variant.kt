package com.example.ordrsmartapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "variants")
data class Variant(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        var concentration: String,
        var flavor: String,
        var weight: String
)
