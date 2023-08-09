package com.example.products.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.products.data.datasource.local.entities.ProductEntity
import com.example.products.data.datasource.remote.Result
import com.example.products.data.datasource.remote.model.Product
import com.example.products.domain.usecases.GetLocalProductsUseCase
import com.example.products.domain.usecases.GetProductsUseCase
import com.example.products.domain.usecases.SaveProductsInLocalDBUseCase
import com.example.products.extensions.toMoneyFormat
import com.example.products.presentation.model.ProductUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getLocalProductsUseCase: GetLocalProductsUseCase,
    private val saveProductsInLocalDBUseCase: SaveProductsInLocalDBUseCase
) : ViewModel() {

    private val productsMLD: MutableLiveData<List<ProductUI>> = MutableLiveData()
    private val showMessageMLD: MutableLiveData<String> = MutableLiveData()
    private val showLoadingMLD: MutableLiveData<Boolean> = MutableLiveData()


    fun getProducts() {
        showLoading(true)
        viewModelScope.launch {
            val products = getLocalProductsUseCase.invoke()
            if (products.isNotEmpty()) {
                //val productsResponse = productsResult.data
                val productsList = products.map { product: ProductEntity ->
                    ProductUI(
                        product.id,
                        product.title,
                        product.description,
                        product.price.toMoneyFormat(),
                        product.thumbnail
                    )
                }
                productsMLD.value = productsList
                showLoading(false)
            } else {
                getRemoteProducts()
            }
        }
    }

    private fun getRemoteProducts() {
        showLoading(true)
        viewModelScope.launch {
            when (val productsResult = getProductsUseCase.invoke()) {
                is Result.Error -> {
                    showMessageMLD.value = productsResult.exception.message.orEmpty()
                    showLoading(false)
                }

                is Result.Success -> {
                    val productsResponse = productsResult.data
                    val productsList = productsResponse.products.map { product: Product ->
                        ProductUI(
                            product.id,
                            product.title,
                            product.description,
                            product.price.toMoneyFormat(),
                            product.thumbnail
                        )
                    }
                    saveProductsInLocalDB(productsResponse.products)
                    productsMLD.value = productsList
                    showLoading(false)
                }
            }
        }
    }

    private fun saveProductsInLocalDB(products: List<Product>) {
        val productsToSave = products.map { p ->
            ProductEntity(
                id = p.id,
                title = p.title,
                description = p.description,
                price = p.price,
                discountPercentage = p.discountPercentage,
                rating = p.rating,
                stock = p.stock,
                brand = p.brand,
                thumbnail = p.thumbnail,
                category = p.category
            )
        }
        viewModelScope.launch {
            saveProductsInLocalDBUseCase.invoke(productsToSave)
        }
    }

    private fun showLoading(isShow: Boolean) {
        showLoadingMLD.value = isShow
    }

    fun onProducts(): LiveData<List<ProductUI>> = productsMLD
    fun onShowMessage(): LiveData<String> = showMessageMLD
    fun onShowLoading(): LiveData<Boolean> = showLoadingMLD

}