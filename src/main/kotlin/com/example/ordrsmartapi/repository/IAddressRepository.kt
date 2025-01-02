package com.example.ordrsmartapi.repository // without package namespace, repository won't be recognised by Bean Scanner for DI

import com.example.ordrsmartapi.entity.Address
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Repository interface for Address entities.
 * Provides CRUD operations for Address entities.
 * Extends CrudRepository to handle data access for Address entities with Long as the primary key.
 */
@Repository
interface IAddressRepository: CrudRepository<Address, Long> {
}

