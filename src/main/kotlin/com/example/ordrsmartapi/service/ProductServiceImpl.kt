package com.example.ordrsmartapi.service

import com.example.ordrsmartapi.dto.ProductDTO
import com.example.ordrsmartapi.entity.Product
import com.example.ordrsmartapi.repository.IProductRepository
import com.example.ordrsmartapi.service.interfaces.IProductService
import com.example.ordrsmartapi.utils.exception.ProductException
import com.example.ordrsmartapi.utils.mapper.EntityDtoMapper
import com.example.ordrsmartapi.utils.mapper.IEntityDtoMapper
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
        private val productRepository: IProductRepository,
        private val mapper: IEntityDtoMapper,
        ) : IProductService {
    override fun createProduct(productDTO: ProductDTO): ProductDTO {
        if (productDTO.id.toInt() != -1) {
            throw ProductException("Product Id should not be provided")
        }

        val productDtoToEntity = mapper.fromProductDtoToEntity(productDTO)
        val product = productRepository.save(productDtoToEntity)
        return mapper.fromProductEntityToDto(product)
    }

    override fun getProduct(id: Long): ProductDTO {
        val optionalProduct = productRepository.findById(id)
        val product = optionalProduct.orElseThrow { ProductException("Product with id $id is not present") }
        return mapper.fromProductEntityToDto(product)
    }

    override fun updateProduct(movieDTO: ProductDTO): ProductDTO {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(id: Int) {
        TODO("Not yet implemented")
    }
}



