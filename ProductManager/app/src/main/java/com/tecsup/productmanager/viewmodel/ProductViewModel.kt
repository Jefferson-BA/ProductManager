package com.tecsup.productmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.productmanager.data.model.Product
import com.tecsup.productmanager.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repo: ProductRepository = ProductRepository()
) : ViewModel() {

    val products = repo.products

    fun loadProducts(userId: String) {
        repo.listenProducts(userId)
    }

    fun saveProduct(product: Product) {
        viewModelScope.launch {
            if (product.id.isEmpty()) {
                repo.addProduct(product)
            } else {
                repo.updateProduct(product)
            }
        }
    }

    fun deleteProduct(id: String) {
        viewModelScope.launch {
            repo.deleteProduct(id)
        }
    }
}
