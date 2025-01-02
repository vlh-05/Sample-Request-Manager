package com.example.ordrsmartapi.service.interfaces

import com.example.ordrsmartapi.dto.ProductDTO
import com.example.ordrsmartapi.dto.VariantReadDTO

interface IVariantService {
    fun getVariant(id: Long): VariantReadDTO
}