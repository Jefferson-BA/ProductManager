package com.tecsup.productmanager.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tecsup.productmanager.data.model.Product

@Composable
fun ProductItem(
    product: Product,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(product.name, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(6.dp))

            Text("Precio: S/. ${product.price}")
            Text("Stock: ${product.stock}")
            Text("Categoría: ${product.category}")

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                TextButton(onClick = onEdit) {
                    Text("Editar")
                }

                TextButton(onClick = onDelete) {
                    Text("Eliminar")
                }
            }
        }
    }
}
