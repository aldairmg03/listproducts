package com.example.products.di

import com.example.products.data.datasource.remote.ProductRemoteDataSource
import com.example.products.data.datasource.remote.ProductRemoteDataSourceImpl
import com.example.products.data.datasource.remote.ProductService
import com.example.products.data.repository.ProductRepository
import com.example.products.data.repository.ProductRepositoryImpl
import com.example.products.di.annotations.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {

    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)

    @Provides
    @Singleton
    fun provideProductRemoteDataSource(productService: ProductService): ProductRemoteDataSource =
        ProductRemoteDataSourceImpl(productService)

    @Provides
    @Singleton
    fun provideProductRepository(
        @IoDispatcher dispatcherIO: CoroutineDispatcher,
        productRemoteDataSource: ProductRemoteDataSource
    ): ProductRepository = ProductRepositoryImpl(dispatcherIO, productRemoteDataSource)

}