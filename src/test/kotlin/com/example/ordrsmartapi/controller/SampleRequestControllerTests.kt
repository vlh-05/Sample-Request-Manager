package com.example.ordrsmartapi.controller

import com.example.ordrsmartapi.dto.*
import com.example.ordrsmartapi.service.interfaces.ISampleRequestService
import com.example.ordrsmartapi.utils.exception.SampleRequestException
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDateTime
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ExtendWith(MockitoExtension::class)
@WebMvcTest(SampleRequestController::class)
class SampleRequestControllerTests {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var sampleRequestService: ISampleRequestService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(SampleRequestController(sampleRequestService)).build()
    }

    @Test
    fun `test getSampleRequest success`() {
        val sampleRequestReadDto = SampleRequestReadDto(
                id = 1L,
                product = ProductDTO(1L, "Product"),
                variant = VariantReadDTO(1L, VariantInnerDTO("50%", "Vanilla", "250g")),
                sampleQuantity = "10",
                sampleApplication = "Testing",
                shippingAddress = AddressDTO("123 Main St", "", "Chicago", "IL", "60601"),
                additionalInformation = "Info",
                createdAt = LocalDateTime.now(),
                createdBy = "User"
        )
        val responseOfSampleRequestDto = ResponseOfSampleRequestDto(
                success = true,
                message = "successfully got sample request",
                sampleRequest = sampleRequestReadDto
        )

        `when`(sampleRequestService.getSampleRequestById(1L)).thenReturn(responseOfSampleRequestDto)

        mockMvc.perform(
                get("/sample-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(SampleRequestGetDto(sample_request_id = 1L)))
        )
        .andExpect(status().isOk)
        .andExpect(jsonPath("$.sampleRequest.id").value(1L))
        .andExpect(jsonPath("$.sampleRequest.product.name").value("Product"))
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
        val sampleRequestReadDto = SampleRequestReadDto(
                id = 1L,
                product = ProductDTO(1L, "Product"),
                variant = VariantReadDTO(1L, VariantInnerDTO("50%", "Vanilla", "250g")),
                sampleQuantity = "10",
                sampleApplication = "Testing",
                shippingAddress = AddressDTO("123 Main St", "", "Chicago", "IL", "60601"),
                additionalInformation = "Info",
                createdAt = LocalDateTime.now(),
                createdBy = "User"
        )
        val responseOfSampleRequestDto = ResponseOfSampleRequestDto(
                success = true,
                message = "Sample request created successfully",
                sampleRequest = sampleRequestReadDto
        )

        `when`(sampleRequestService.createSampleRequestById(sampleRequestCreateDTO)).thenReturn(responseOfSampleRequestDto)

        mockMvc.perform(
                post("/sample-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleRequestCreateDTO))
        )
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.sampleRequest.id").value(1L))
                .andExpect(jsonPath("$.sampleRequest.product.name").value("Product"))
    }
}