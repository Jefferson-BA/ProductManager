package com.tecsup.productmanager.ui.screens

import androidx.compose.runtime.Composable
import com.tecsup.productmanager.data.model.Product
import com.tecsup.productmanager.viewmodel.ProductViewModel

@Composable
fun ProductFormScreen(
    viewModel: ProductViewModel,
    product: Product?,
    userId: String,
    onSaved: () -> Unit
) {
}
