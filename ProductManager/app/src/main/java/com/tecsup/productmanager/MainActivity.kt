package com.tecsup.productmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.google.firebase.FirebaseApp
import com.tecsup.productmanager.ui.screens.*
import com.tecsup.productmanager.viewmodel.AuthViewModel
import com.tecsup.productmanager.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {

    private val authVM: AuthViewModel by viewModels()
    private val productVM: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        setContent {
            MaterialTheme {
                Surface {

                    val navController = rememberNavController()

                    // Verificar si hay usuario logueado
                    val userId = authVM.currentUserId()
                    val startDestination = if (userId == null) "login" else "products"

                    NavHost(
                        navController = navController,
                        startDestination = startDestination
                    ) {

                        // LOGIN
                        composable("login") {
                            LoginScreen(
                                viewModel = authVM,
                                onSuccess = {
                                    navController.navigate("products") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onRegisterClick = {
                                    navController.navigate("register")
                                }
                            )
                        }

                        // REGISTER
                        composable("register") {
                            RegisterScreen(
                                viewModel = authVM,
                                onSuccess = {
                                    navController.navigate("products") {
                                        popUpTo("register") { inclusive = true }
                                    }
                                }
                            )
                        }

                        // PRODUCT LIST (PRINCIPAL)
                        composable("products") {

                            val uid = authVM.currentUserId()

                            LaunchedEffect(uid) {
                                if (uid != null) productVM.loadProducts(uid)
                            }

                            ProductListScreen(
                                viewModel = productVM,
                                viewModelAuth = authVM,
                                onCreateClick = {
                                    navController.navigate("productForm")
                                },
                                onEditClick = { product ->
                                    navController.navigate("productForm/${product.id}")
                                },
                                onLogout = {
                                    navController.navigate("login") {
                                        popUpTo("products") { inclusive = true }
                                    }
                                }
                            )
                        }

                        // CREAR PRODUCTO
                        composable("productForm") {
                            ProductFormScreen(
                                viewModel = productVM,
                                product = null,
                                userId = authVM.currentUserId() ?: "",
                                onSaved = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        // EDITAR PRODUCTO
                        composable(
                            route = "productForm/{id}",
                            arguments = listOf(navArgument("id") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->

                            val id = backStackEntry.arguments?.getString("id") ?: ""

                            val products by productVM.products.collectAsState()
                            val product = products.find { it.id == id }

                            ProductFormScreen(
                                viewModel = productVM,
                                product = product,
                                userId = authVM.currentUserId() ?: "",
                                onSaved = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
