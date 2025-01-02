package com.example.ordrsmartapi.service

import com.example.ordrsmartapi.MockitoHelper
import com.example.ordrsmartapi.dto.ProductDTO
import com.example.ordrsmartapi.entity.Product
import com.example.ordrsmartapi.repository.IProductRepository
import com.example.ordrsmartapi.utils.exception.ProductException
import com.example.ordrsmartapi.utils.mapper.IEntityDtoMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness
import java.util.*


@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceImplTest {

    @Mock
    private lateinit var productRepository: IProductRepository

    @Mock
    private lateinit var entityDtoMapper: IEntityDtoMapper

    @InjectMocks
    private lateinit var productService: ProductServiceImpl

    @Test
    fun `test createProduct throws exception if ID is provided`() {
        val productDTO = ProductDTO(id = 1L, name = "Test Product")
        assertThrows(ProductException::class.java) {
            productService.createProduct(productDTO)
        }
    }

    @Test
    fun `test createProduct success`() {
        // Arrange
        val productDTO = ProductDTO(name = "New Product")
        val product = Product(name = "New Product", id = 1L)

        `when`(entityDtoMapper.fromProductDtoToEntity(MockitoHelper.anyObject<ProductDTO>())).thenReturn(product)
        `when`(productRepository.save(any(Product::class.java))).thenReturn(product)
        `when`(entityDtoMapper.fromProductEntityToDto(MockitoHelper.anyObject<Product>())).thenReturn(ProductDTO(id = 1L, name = "New Product"))

        // Act
        val savedProductDTO = productService.createProduct(productDTO)

        // Assert
        assertNotNull(savedProductDTO)
        assertEquals(savedProductDTO.name, "New Product")
        verify(productRepository).save(Product(id = product.id, name = product.name))
    }

    @Test
    fun `test getProduct found`() {
        // Arrange
        val product = Product(id = 1L, name = "Existing Product")
        `when`(productRepository.findById(anyLong())).thenReturn(Optional.of(product))
        `when`(entityDtoMapper.fromProductEntityToDto(MockitoHelper.anyObject<Product>())).thenReturn(ProductDTO(id = 1L, name = "Existing Product"))

        // Act
        val productDTO = productService.getProduct(1L)

        // Assert
        assertNotNull(productDTO)
        assertEquals(1L, productDTO.id)
        assertEquals("Existing Product", productDTO.name)
    }

    @Test
    fun `test getProduct not found`() {
        // Arrange
        `when`(productRepository.findById(any())).thenReturn(Optional.empty())

        // Act
        val exception = assertThrows(ProductException::class.java) {
            productService.getProduct(1L)
        }

        // Assert
        assertEquals("Product with id 1 is not present", exception.message)
    }


}