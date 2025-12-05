package com.tecsup.productmanager.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tecsup.productmanager.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    fun listenProducts(userId: String) {
        db.collection("products")
            .whereEqualTo("userId", userId)
            .addSnapshotListener { value, error ->
                if (error != null) return@addSnapshotListener
                val list = value?.toObjects(Product::class.java) ?: emptyList()
                _products.value = list
            }
    }

    suspend fun addProduct(product: Product) {
        val id = db.collection("products").document().id
        db.collection("products").document(id).set(product.copy(id = id))
    }

    suspend fun updateProduct(product: Product) {
        db.collection("products").document(product.id).set(product)
    }

    suspend fun deleteProduct(id: String) {
        db.collection("products").document(id).delete()
    }
}
