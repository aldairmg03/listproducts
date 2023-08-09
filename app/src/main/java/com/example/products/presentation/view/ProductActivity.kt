package com.example.products.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.products.databinding.ActivityMainBinding
import com.example.products.presentation.model.ProductUI
import com.example.products.presentation.view.adapter.ProductAdapter
import com.example.products.presentation.view.dialog.DialogMessage
import com.example.products.presentation.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initViewModel()
    }

    private fun initView() {
        productAdapter = ProductAdapter()
        binding.recyclerViewProducts.adapter = productAdapter
    }

    private fun initViewModel() {
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        with(productViewModel) {
            onProducts().observe(this@ProductActivity) { products ->
                showData(products)
            }

            onShowMessage().observe(this@ProductActivity) { message ->
                showAlert()
            }

            onShowLoading().observe(this@ProductActivity) { isShow ->
                binding.progressCircular.isVisible = isShow
                binding.progressCircular.isIndeterminate = isShow
            }

        }

        getProducts()
    }

    private fun getProducts() {
        productViewModel.getProducts()
    }

    private fun showData(products: List<ProductUI>) {
        productAdapter.submitList(products)
    }

    private fun showAlert() {
        DialogMessage { dialog, _ ->
            dialog.dismiss()
            getProducts()
        }.show(supportFragmentManager, "DialogMessage")
    }

}