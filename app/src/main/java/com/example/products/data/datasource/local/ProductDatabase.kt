package com.example.products.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.products.constants.ProductConstants
import com.example.products.data.datasource.local.entities.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {

        @Volatile
        private var instance: ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                ProductDatabase::class.java,
                ProductConstants.PRODUCT_DATABASE
            )
                .fallbackToDestructiveMigration()
                .build()

    }

}