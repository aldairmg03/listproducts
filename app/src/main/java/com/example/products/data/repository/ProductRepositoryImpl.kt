package com.example.products.data.repository

import com.example.products.data.datasource.remote.ProductRemoteDataSource
import com.example.products.data.datasource.remote.Result
import com.example.products.data.datasource.remote.model.ProductResponse
import com.example.products.di.annotations.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val productRemoteDataSource: ProductRemoteDataSource
) : ProductRepository {
    override suspend fun getProducts(): Result<ProductResponse> = withContext(dispatcher) {
        productRemoteDataSource.getProducts()
    }

}

