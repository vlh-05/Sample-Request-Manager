package com.example.ordrsmartapi.controller

import com.example.ordrsmartapi.dto.AddressCreateDTO
import com.example.ordrsmartapi.dto.SampleRequestCreateDTO
import com.example.ordrsmartapi.dto.SampleRequestGetDto
import com.example.ordrsmartapi.entity.Address
import com.example.ordrsmartapi.entity.Product
import com.example.ordrsmartapi.entity.SampleRequest
import com.example.ordrsmartapi.entity.Variant
import com.example.ordrsmartapi.repository.IAddressRepository
import com.example.ordrsmartapi.repository.IProductRepository
import com.example.ordrsmartapi.repository.ISampleRequestRepository
import com.example.ordrsmartapi.repository.IVariantRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import java.time.LocalDateTime
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SampleRequestControllerIntegrationTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var productRepository: IProductRepository

    @Autowired
    private lateinit var variantRepository: IVariantRepository

    @Autowired
    private lateinit var addressRepository: IAddressRepository

    @Autowired
    private lateinit var sampleRequestRepository: ISampleRequestRepository

    @BeforeEach
    fun setup() {
        productRepository.save(Product(id = 1, name = "Product"))
        variantRepository.save(Variant(id = 1, concentration = "50%", flavor = "Vanilla", weight = "250g"))
        addressRepository.save(Address(id = 1, line1 = "123 Main St", line2 = "", city = "Chicago", state = "IL", zipCode = "60601"))
    }

    @AfterEach
    fun cleanup() {
        sampleRequestRepository.deleteAll()
        addressRepository.deleteAll()
        variantRepository.deleteAll()
        productRepository.deleteAll()
    }

    @Test
    fun `test getSampleRequest success`() {
        val y = productRepository.findAll()
        val y2 = variantRepository.findAll()
        val y3 = addressRepository.findAll()
        val y4 = sampleRequestRepository.findAll()
        val sampleRequest = sampleRequestRepository.save(
                SampleRequest(
                        id = 1,
                        product = productRepository.findById(11).get(),
                        variant = variantRepository.findById(11).get(),
                        sampleQuantity = "10",
                        sampleApplication = "Testing",
                        shippingAddress = addressRepository.findById(12).get(),
                        createdBy = "User",
                        additionalInformation = "Info",
                        createdAt = LocalDateTime.now()
                )
        )

        mockMvc.perform(
                get("/sample-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(SampleRequestGetDto(sample_request_id = sampleRequest.id)))
        )
        .andExpect(status().isOk)
        .andExpect(jsonPath("$.sampleRequest.id").value(sampleRequest.id))
        .andExpect(jsonPath("$.sampleRequest.product.name").value(sampleRequest.product.name))
    }

    @Test
    fun `test getSampleRequest not found`() {
        mockMvc.perform(
                get("/sample-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(SampleRequestGetDto(sample_request_id = 999L)))
        )
        .andExpect(status().isNotFound)
        .andExpect(jsonPath("$.message").value("SampleRequest with id 999 is not present"))
    }

    @Test
    fun `test createSampleRequest success`() {
        val sampleRequestCreateDTO = SampleRequestCreateDTO(
                product_id = 1L,
                variant_id = 1L,
                sample_quantity = "10",
                sample_application = "Testing",
                shipping_address = AddressCreateDTO("123 Main St", "", "Chicago", "IL", "60601"),
                additional_information = "Info",
                requestor = "User"
        )

        mockMvc.perform(
                post("/sample-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleRequestCreateDTO))
        )
        .andExpect(status().isOk)
        .andExpect(jsonPath("$.sampleRequest.product.name").value("Product"))
        .andExpect(jsonPath("$.sampleRequest.variant.values.flavor").value("Vanilla"))
    }

}