package com.example.products.data.repository

import com.example.products.data.datasource.remote.Result
import com.example.products.data.datasource.remote.model.ProductResponse

interface ProductRepository {

    suspend fun getProducts(): Result<ProductResponse>

}