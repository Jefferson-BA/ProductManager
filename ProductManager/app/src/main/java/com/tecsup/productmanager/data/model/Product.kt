package com.tecsup.productmanager.data.model

data class Product(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val stock: Int = 0,
    val category: String = "",
    val userId: String = "" // para filtrar por usuario
)
