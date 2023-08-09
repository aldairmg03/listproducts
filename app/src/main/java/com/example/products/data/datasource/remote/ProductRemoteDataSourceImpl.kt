package com.example.products.data.datasource.remote

import com.example.products.base.BaseDataSource
import com.example.products.data.datasource.remote.model.ProductResponse
import javax.inject.Inject

class ProductRemoteDataSourceImpl @Inject constructor(
    private val productService: ProductService
) : BaseDataSource(), ProductRemoteDataSource {
    override suspend fun getProducts(): Result<ProductResponse> = getResponse {
        productService.getProducts()
    }
}