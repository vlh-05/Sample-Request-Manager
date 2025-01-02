package com.example.ordrsmartapi.service

import com.example.ordrsmartapi.dto.AddressCreateDTO
import com.example.ordrsmartapi.dto.AddressDTO
import com.example.ordrsmartapi.entity.Address
import com.example.ordrsmartapi.utils.exception.AddressException
import com.example.ordrsmartapi.repository.IAddressRepository
import com.example.ordrsmartapi.service.interfaces.IAddressService
import com.example.ordrsmartapi.utils.mapper.IEntityDtoMapper
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(
        private val addressRepository: IAddressRepository,
        private val mapper: IEntityDtoMapper,
) : IAddressService {
    override fun createAddress(addressDTO: AddressCreateDTO): AddressDTO {
        val address = addressRepository.save(mapper.mapAddressCreateDtotoEntity(addressDTO))
        return mapper.mapAddressEntityToDto(address)
    }

    override fun getAddress(id: Long): AddressDTO {
        val optionalAddress = addressRepository.findById(id)
        val address = optionalAddress.orElseThrow { AddressException("Address with id $id is not present") }
        return mapper.mapAddressEntityToDto(address)
    }

}

