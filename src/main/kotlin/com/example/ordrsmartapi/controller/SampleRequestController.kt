package com.example.ordrsmartapi.controller

import com.example.ordrsmartapi.dto.SampleRequestGetDto
import com.example.ordrsmartapi.dto.ResponseOfSampleRequestDto
import com.example.ordrsmartapi.dto.SampleRequestCreateDTO
import com.example.ordrsmartapi.service.interfaces.ISampleRequestService
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SampleRequestController (
        private val sampleRequestService: ISampleRequestService)  {

    @GetMapping("/sample-requests")     //  @GetMapping("/sample-requests/{id}")    // @PathVariable id
    fun getSampleRequest(@RequestBody sampleRequestGetDto: SampleRequestGetDto): ResponseEntity<ResponseOfSampleRequestDto> {
        return ResponseEntity.ok(sampleRequestService.getSampleRequestById(sampleRequestGetDto.sample_request_id))
    }

    @PostMapping("/sample-requests")
    fun createSampleRequest(@RequestBody sampleRequestCreateDto: SampleRequestCreateDTO): ResponseEntity<ResponseOfSampleRequestDto> {
        return ResponseEntity.ok(sampleRequestService.createSampleRequestById(sampleRequestCreateDto))
    }
}