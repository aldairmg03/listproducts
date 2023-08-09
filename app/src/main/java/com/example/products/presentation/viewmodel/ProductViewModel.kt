package com.example.products.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.products.data.datasource.remote.Result
import com.example.products.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {


    fun getProducts() {
        viewModelScope.launch {
            when (val productsResult = getProductsUseCase.invoke()) {
                is Result.Error -> {
                    Log.i("Error", productsResult.exception.message.orEmpty())
                }

                is Result.Success -> {
                    Log.i(
                        "Success",
                        productsResult.data.products.firstOrNull()?.thumbnail.orEmpty()
                    )
                }
            }
        }
    }

}