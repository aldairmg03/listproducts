package com.example.products.di

import android.content.Context
import com.example.products.data.datasource.local.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideProductDatabase(
        @ApplicationContext appContext: Context
    ) = ProductDatabase.getDatabase(appContext)

    @Provides
    fun provideProductDao(db: ProductDatabase) = db.productDao()


}