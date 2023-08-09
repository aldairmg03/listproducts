package com.example.products.domain.usecases

import com.example.products.data.datasource.remote.Result
import com.example.products.data.datasource.remote.model.ProductResponse
import com.example.products.data.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend fun invoke(): Result<ProductResponse> = productRepository.getProducts()

}