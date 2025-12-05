package com.tecsup.productmanager.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tecsup.productmanager.data.model.Product
import com.tecsup.productmanager.ui.components.ProductItem
import com.tecsup.productmanager.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    viewModel: ProductViewModel,
    onCreateClick: () -> Unit,
    onEditClick: (Product) -> Unit
) {
    val list by viewModel.products.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onCreateClick) {
                Icon(Icons.Default.Add, contentDescription = "Nuevo")
            }
        },
        topBar = {
            TopAppBar(title = { Text("Mis Productos") })
        }
    ) { padding ->

        LazyColumn(modifier = Modifier.padding(padding).padding(12.dp)) {
            items(list) { product ->
                ProductItem(
                    product = product,
                    onEdit = { onEditClick(product) },
                    onDelete = { viewModel.deleteProduct(product.id) }
                )
            }
        }
    }
}
