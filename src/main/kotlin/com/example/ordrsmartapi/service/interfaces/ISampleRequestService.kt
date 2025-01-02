package com.example.ordrsmartapi.service.interfaces

import com.example.ordrsmartapi.dto.SampleRequestCreateDTO
import com.example.ordrsmartapi.dto.ResponseOfSampleRequestDto

interface ISampleRequestService {
    fun getSampleRequestById(id: Long): ResponseOfSampleRequestDto
    fun createSampleRequestById(sampleRequestCreateDto: SampleRequestCreateDTO): ResponseOfSampleRequestDto
}