package com.example.ordrsmartapi.service.interfaces

import com.example.ordrsmartapi.dto.AddressCreateDTO
import com.example.ordrsmartapi.dto.AddressDTO
import com.example.ordrsmartapi.dto.ProductDTO

interface IAddressService {
    fun createAddress(addressDTO: AddressCreateDTO): AddressDTO
    fun getAddress(id: Long): AddressDTO
}