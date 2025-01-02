package com.example.ordrsmartapi.utils.mapper

import com.example.ordrsmartapi.dto.*
import com.example.ordrsmartapi.entity.Address
import com.example.ordrsmartapi.entity.Product
import org.springframework.stereotype.Service


@Service
class EntityDtoMapper: IEntityDtoMapper {
    override fun fromProductDtoToEntity(domain: ProductDTO): Product = Product (
        domain.id,
        domain.name
    )

    override fun fromProductEntityToDto(entity: Product): ProductDTO = ProductDTO (
        entity.id,
        entity.name,
    )

    override fun mapAddressEntityToDto(address: Address): AddressDTO = AddressDTO(
            line1 = address.line1,
            line2 = address.line2,
            city = address.city,
            state = address.state,
            zipCode = address.zipCode
    )

    override fun mapAddressCreateDtotoEntity(address: AddressCreateDTO): Address = Address(
            line1 = address.line_1,
            line2 = address.line_2,
            city = address.city,
            state = address.state,
            zipCode = address.zip_code,
            id = -1
    )
}