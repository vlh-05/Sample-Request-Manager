package com.example.ordrsmartapi.utils.mapper

import com.example.ordrsmartapi.dto.AddressCreateDTO
import com.example.ordrsmartapi.dto.AddressDTO
import com.example.ordrsmartapi.dto.ProductDTO
import com.example.ordrsmartapi.entity.Address
import com.example.ordrsmartapi.entity.Product


interface IEntityDtoMapper {
    fun fromProductEntityToDto(entity: Product): ProductDTO
    fun fromProductDtoToEntity(domain: ProductDTO): Product
    fun mapAddressEntityToDto(domain: Address): AddressDTO
    fun mapAddressCreateDtotoEntity(domain: AddressCreateDTO): Address

}