package com.example.ordrsmartapi.repository

import com.example.ordrsmartapi.entity.Address
import com.example.ordrsmartapi.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IProductRepository : CrudRepository<Product, Long> {
}
