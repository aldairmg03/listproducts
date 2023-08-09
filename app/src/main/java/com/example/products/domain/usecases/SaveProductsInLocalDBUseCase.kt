package com.example.products.domain.usecases

import com.example.products.data.datasource.local.entities.ProductEntity
import com.example.products.data.repository.ProductRepository
import javax.inject.Inject

class SaveProductsInLocalDBUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend fun invoke(products: List<ProductEntity>) = productRepository.saveProducts(products)
}