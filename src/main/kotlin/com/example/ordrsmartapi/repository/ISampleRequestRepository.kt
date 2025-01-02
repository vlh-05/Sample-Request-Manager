package com.example.ordrsmartapi.repository

import com.example.ordrsmartapi.entity.SampleRequest
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
public interface ISampleRequestRepository : CrudRepository<SampleRequest, Long> {
}