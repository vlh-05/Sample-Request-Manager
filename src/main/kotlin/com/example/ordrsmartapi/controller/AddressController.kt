package com.example.ordrsmartapi.controller

import com.example.ordrsmartapi.dto.AddressCreateDTO
import com.example.ordrsmartapi.dto.AddressDTO
import com.example.ordrsmartapi.service.interfaces.IAddressService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class AddressController (
        private val addressService: IAddressService)  {

    @GetMapping("/addresses/{id}")
    fun getAddressById(@PathVariable id: Long): ResponseEntity<AddressDTO> {
        return ResponseEntity.ok(addressService.getAddress(id))
    }

    @PostMapping("/addresses")
    fun createAddress(@RequestBody addressCreateDTO: AddressCreateDTO): ResponseEntity<AddressDTO> {
        return ResponseEntity.ok(addressService.createAddress(addressCreateDTO))
    }
}