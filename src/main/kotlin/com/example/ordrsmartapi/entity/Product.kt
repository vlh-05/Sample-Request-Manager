package com.example.ordrsmartapi.entity

import jakarta.persistence.*


@Entity
@Table(name = "products")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        val name: String
)
