package com.example.ordrsmartapi.service.interfaces

import com.example.ordrsmartapi.dto.ProductDTO
import com.example.ordrsmartapi.entity.Product

interface IProductService {
    fun createProduct(productDTO: ProductDTO): ProductDTO

    fun getProduct(id: Long): ProductDTO

    fun updateProduct(movieDTO: ProductDTO): ProductDTO

    fun deleteProduct(id: Int)
}

