package com.example.ordrsmartapi.service

import com.example.ordrsmartapi.dto.*
import com.example.ordrsmartapi.entity.Address
import com.example.ordrsmartapi.entity.SampleRequest
import com.example.ordrsmartapi.repository.IAddressRepository
import com.example.ordrsmartapi.repository.IProductRepository
import com.example.ordrsmartapi.repository.ISampleRequestRepository
import com.example.ordrsmartapi.repository.IVariantRepository
import com.example.ordrsmartapi.service.interfaces.ISampleRequestService
import com.example.ordrsmartapi.utils.exception.SampleRequestException
import com.example.ordrsmartapi.utils.mapper.EntityDtoMapper
import com.example.ordrsmartapi.utils.mapper.IEntityDtoMapper
import org.springframework.stereotype.Service


@Service
class SampleRequestServiceImpl(
        private val sampleRequestRepository: ISampleRequestRepository,
        private val productRepository: IProductRepository,
        private val variantRepository: IVariantRepository,
        private val addressRepository: IAddressRepository,
        private val mapper: IEntityDtoMapper,
        ) : ISampleRequestService {
    override fun getSampleRequestById(id: Long): ResponseOfSampleRequestDto {
        val optionalSampleRequest = sampleRequestRepository.findById(id)
        val sampleRequestEntity = optionalSampleRequest.orElseThrow { SampleRequestException("SampleRequest with id $id is not present") }
        return mapSampleRequestEntityToResponseDTO(sampleRequestEntity)
    }

    override fun createSampleRequestById(sampleRequestCreateDto: SampleRequestCreateDTO): ResponseOfSampleRequestDto {
        val optionalProduct = productRepository.findById(sampleRequestCreateDto.product_id)
        val product = optionalProduct.orElseThrow { SampleRequestException("SampleRequest can't be created with invalid productId ${sampleRequestCreateDto.product_id}")}

        val optionalVariant = variantRepository.findById(sampleRequestCreateDto.variant_id)
        val variant = optionalVariant.orElseThrow { SampleRequestException("SampleRequest can't be created with invalid variantId ${sampleRequestCreateDto.variant_id}")}

        val addressEntity = addressRepository.save(
                mapper.mapAddressCreateDtotoEntity(sampleRequestCreateDto.shipping_address))

        var sampleRequestEntity = SampleRequest(
                product = product,
                variant = variant,
                sampleQuantity = sampleRequestCreateDto.sample_quantity,
                sampleApplication = sampleRequestCreateDto.sample_application,
                shippingAddress = addressEntity,
                createdBy = sampleRequestCreateDto.requestor,
                additionalInformation = sampleRequestCreateDto.additional_information,
                id = -1
        )
        sampleRequestEntity = sampleRequestRepository.save(sampleRequestEntity)
        return mapSampleRequestEntityToResponseDTO(sampleRequestEntity)
    }

    private fun mapSampleRequestEntityToResponseDTO(sampleRequestEntity: SampleRequest): ResponseOfSampleRequestDto {
        return ResponseOfSampleRequestDto(
                success = true,
                message = "successfully got sample request",
                sampleRequest =  SampleRequestReadDto(
                        id = sampleRequestEntity.id,
                        product = ProductDTO(
                                id = sampleRequestEntity.product.id,
                                name = sampleRequestEntity.product.name),
                        variant = VariantReadDTO(
                                id = sampleRequestEntity.variant.id,
                                values = VariantInnerDTO(
                                        concentration = sampleRequestEntity.variant.concentration,
                                        flavor = sampleRequestEntity.variant.flavor,
                                        weight = sampleRequestEntity.variant.weight)
                        ),
                        sampleQuantity = sampleRequestEntity.sampleQuantity,
                        sampleApplication = sampleRequestEntity.sampleApplication,
                        shippingAddress = mapper.mapAddressEntityToDto(sampleRequestEntity.shippingAddress),
                        additionalInformation = sampleRequestEntity.additionalInformation,
                        createdAt = sampleRequestEntity.createdAt,
                        createdBy = sampleRequestEntity.createdBy
                )
        )
    }
}