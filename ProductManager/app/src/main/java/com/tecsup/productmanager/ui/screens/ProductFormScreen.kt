package com.tecsup.productmanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tecsup.productmanager.data.model.Product
import com.tecsup.productmanager.viewmodel.ProductViewModel

@Composable
fun ProductFormScreen(
    viewModel: ProductViewModel,
    product: Product?,
    userId: String,
    onSaved: () -> Unit
) {
    var name by remember { mutableStateOf(product?.name ?: "") }
    var price by remember { mutableStateOf(product?.price?.toString() ?: "") }
    var stock by remember { mutableStateOf(product?.stock?.toString() ?: "") }
    var category by remember { mutableStateOf(product?.category ?: "") }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {

        Text(
            text = if (product == null) "Crear Producto" else "Editar Producto",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
        OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Precio") })
        OutlinedTextField(value = stock, onValueChange = { stock = it }, label = { Text("Stock") })
        OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Categoría") })

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                val newProduct = Product(
                    id = product?.id ?: "",
                    name = name,
                    price = price.toDoubleOrNull() ?: 0.0,
                    stock = stock.toIntOrNull() ?: 0,
                    category = category,
                    userId = userId
                )

                viewModel.saveProduct(newProduct)
                onSaved()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}
