package com.example.products.presentation.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.products.databinding.ItemProductBinding
import com.example.products.extensions.loadImage
import com.example.products.presentation.model.ProductUI

class ProductViewHolder(private val binding: ItemProductBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductUI) {
        with(binding) {
            imageViewProduct.loadImage(product.thumbnail)
            textViewName.text = product.title
            textViewPrice.text = product.price
        }
    }

}