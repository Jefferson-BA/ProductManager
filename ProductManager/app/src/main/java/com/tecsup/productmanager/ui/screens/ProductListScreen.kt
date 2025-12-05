package com.tecsup.productmanager.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.tecsup.productmanager.data.model.Product
import com.tecsup.productmanager.ui.components.ProductItem
import com.tecsup.productmanager.viewmodel.ProductViewModel

@Composable
fun ProductListScreen(
    viewModel: ProductViewModel,
    onCreateClick: () -> Unit,
    onEditClick: (Product) -> Unit
) {
    val list by viewModel.products.collectAsState()

    LazyColumn {
        items(list) { product ->
            ProductItem(
                product = product,
                onEdit = { onEditClick(product) },
                onDelete = { viewModel.deleteProduct(product.id) }
            )
        }
    }

    FloatingActionButton(onClick = onCreateClick) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}
