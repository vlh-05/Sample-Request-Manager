package com.example.ordrsmartapi.service

import com.example.ordrsmartapi.MockitoHelper
import com.example.ordrsmartapi.dto.AddressCreateDTO
import com.example.ordrsmartapi.dto.AddressDTO
import com.example.ordrsmartapi.dto.SampleRequestCreateDTO
import com.example.ordrsmartapi.entity.Address
import com.example.ordrsmartapi.entity.Product
import com.example.ordrsmartapi.entity.SampleRequest
import com.example.ordrsmartapi.entity.Variant
import com.example.ordrsmartapi.repository.IAddressRepository
import com.example.ordrsmartapi.repository.IProductRepository
import com.example.ordrsmartapi.repository.ISampleRequestRepository
import com.example.ordrsmartapi.repository.IVariantRepository
import com.example.ordrsmartapi.utils.exception.SampleRequestException
import com.example.ordrsmartapi.utils.mapper.IEntityDtoMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime
import java.util.*


@ExtendWith(MockitoExtension::class)
class SampleRequestServiceImplTest {

    @Mock
    private lateinit var sampleRequestRepository: ISampleRequestRepository

    @Mock
    private lateinit var productRepository: IProductRepository

    @Mock
    private lateinit var variantRepository: IVariantRepository

    @Mock
    private lateinit var addressRepository: IAddressRepository

    @Mock
    private lateinit var mapper: IEntityDtoMapper

    @InjectMocks
    private lateinit var sampleRequestService: SampleRequestServiceImpl

    @Test
    fun `test getSampleRequestById found`() {
        // Arrange
        val product = Product(id = 1L, name = "Product")
        val variant = Variant(id = 1L, concentration = "50%", flavor = "Vanilla", weight = "250g")
        val address = Address(id = 1L, line1 = "123 Main St", line2 = "", city = "Chicago", state = "IL", zipCode = "60601")
        val sampleRequest = SampleRequest(
                id = 1L,
                product = product,
                variant = variant,
                sampleQuantity = "10",
                sampleApplication = "Testing",
                shippingAddress = address,
                createdBy = "User",
                additionalInformation = "Info",
                createdAt = LocalDateTime.now()
        )
        `when`(sampleRequestRepository.findById(anyLong())).thenReturn(Optional.of(sampleRequest))
        `when`(mapper.mapAddressEntityToDto(MockitoHelper.anyObject<Address>()))
                .thenReturn(AddressDTO("123 Main St", "", "Chicago", "IL", "60601"))

        // Act
        val response = sampleRequestService.getSampleRequestById(1L)

        // Asset
        assertNotNull(response)
        assertEquals(1L, response.sampleRequest.id)
        assertEquals("Product", response.sampleRequest.product.name)
    }

    @Test
    fun `test getSampleRequestById not found`() {
        // Arrange
        `when`(sampleRequestRepository.findById(anyLong())).thenReturn(Optional.empty())

        // Act
        val exception = assertThrows(SampleRequestException::class.java) {
            sampleRequestService.getSampleRequestById(1L)
        }

        // Assert
        assertEquals("SampleRequest with id 1 is not present", exception.message)
    }

    @Test
    fun `test createSampleRequestById success`() {
        // Arrange
        val sampleRequestCreateDTO = SampleRequestCreateDTO(
                product_id = 1L,
                variant_id = 1L,
                sample_quantity = "10",
                sample_application = "Testing",
                shipping_address = AddressCreateDTO("123 Main St", "", "Chicago", "IL", "60601"),
                additional_information = "Info",
                requestor = "User"
        )
        val product = Product(id = 1L, name = "Product")
        val variant = Variant(id = 1L, concentration = "50%", flavor = "Vanilla", weight = "250g")
        val address = Address(id = 1L, line1 = "123 Main St", line2 = "", city = "Chicago", state = "IL", zipCode = "60601")
        val sampleRequest = SampleRequest(
                id = 1L,
                product = product,
                variant = variant,
                sampleQuantity = "10",
                sampleApplication = "Testing",
                shippingAddress = address,
                createdBy = "User",
                additionalInformation = "Info",
                createdAt = LocalDateTime.now()
        )
        `when`(productRepository.findById(anyLong())).thenReturn(Optional.of(product))
        `when`(variantRepository.findById(anyLong())).thenReturn(Optional.of(variant))
        `when`(addressRepository.save(any(Address::class.java))).thenReturn(address)
        `when`(sampleRequestRepository.save(any(SampleRequest::class.java))).thenReturn(sampleRequest)
        `when`(mapper.mapAddressCreateDtotoEntity(MockitoHelper.anyObject<AddressCreateDTO>())).thenReturn(address)
        `when`(mapper.mapAddressEntityToDto(MockitoHelper.anyObject<Address>())).thenReturn(AddressDTO("123 Main St", "", "Chicago", "IL", "60601"))

        // Act
        val response = sampleRequestService.createSampleRequestById(sampleRequestCreateDTO)

        // Assert
        assertNotNull(response)
        assertEquals("Product", response.sampleRequest.product.name)
        assertEquals("Vanilla", response.sampleRequest.variant.values.flavor)
        assertEquals("123 Main St", response.sampleRequest.shippingAddress.line1)
    }

    @Test
    fun `test createSampleRequestById invalid product`() {
        // Arrange
        val sampleRequestCreateDTO = SampleRequestCreateDTO(
                product_id = 1L,
                variant_id = 1L,
                sample_quantity = "10",
                sample_application = "Testing",
                shipping_address = AddressCreateDTO("123 Main St", "", "Chicago", "IL", "60601"),
                additional_information = "Info",
                requestor = "User"
        )
        `when`(productRepository.findById(anyLong())).thenReturn(Optional.empty())

        // Act
        val exception = assertThrows(SampleRequestException::class.java) {
            sampleRequestService.createSampleRequestById(sampleRequestCreateDTO)
        }

        // Assert
        assertEquals("SampleRequest can't be created with invalid productId 1", exception.message)
    }

    @Test
    fun `test createSampleRequestById invalid variant`() {
        // Arrange
        val sampleRequestCreateDTO = SampleRequestCreateDTO(
                product_id = 1L,
                variant_id = 1L,
                sample_quantity = "10",
                sample_application = "Testing",
                shipping_address = AddressCreateDTO("123 Main St", "", "Chicago", "IL", "60601"),
                additional_information = "Info",
                requestor = "User"
        )

        val product = Product(id = 1L, name = "Product")

        `when`(productRepository.findById(anyLong())).thenReturn(Optional.of(product))
        `when`(variantRepository.findById(anyLong())).thenReturn(Optional.empty())

        // Act
        val exception = assertThrows(SampleRequestException::class.java) {
            sampleRequestService.createSampleRequestById(sampleRequestCreateDTO)
        }

        // Assert
        assertEquals("SampleRequest can't be created with invalid variantId 1", exception.message)
    }
}