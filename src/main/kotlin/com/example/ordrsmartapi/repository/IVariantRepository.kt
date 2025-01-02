package com.example.ordrsmartapi.repository


import com.example.ordrsmartapi.entity.Variant
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface IVariantRepository : CrudRepository<Variant, Long> {
}
