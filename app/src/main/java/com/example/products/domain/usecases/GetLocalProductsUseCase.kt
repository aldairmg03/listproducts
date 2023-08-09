package com.example.products.domain.usecases

import com.example.products.data.datasource.local.entities.ProductEntity
import com.example.products.data.repository.ProductRepository
import javax.inject.Inject

class GetLocalProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend fun invoke(): List<ProductEntity> = productRepository.getProductsLocal()

}