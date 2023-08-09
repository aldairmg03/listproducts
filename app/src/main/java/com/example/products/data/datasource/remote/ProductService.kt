package com.example.products.data.datasource.remote

import com.example.products.data.datasource.remote.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getProducts(): Response<ProductResponse>

}