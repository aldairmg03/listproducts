package com.example.products.data.datasource.remote

import com.example.products.data.datasource.remote.model.ProductResponse
import retrofit2.Response

interface ProductRemoteDataSource {

    suspend fun getProducts(): Result<ProductResponse>

}