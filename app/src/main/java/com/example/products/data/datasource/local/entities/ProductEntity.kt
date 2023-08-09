package com.example.products.data.datasource.local.entities

import androidx.room.Entity

@Entity(primaryKeys = [("id")])
data class ProductEntity(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String
)
