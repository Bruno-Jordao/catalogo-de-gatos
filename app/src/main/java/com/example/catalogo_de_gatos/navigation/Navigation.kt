package com.example.catalogo_de_gatos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catalogo_de_gatos.screens.FavoritesScreen
import com.example.catalogo_de_gatos.screens.HomeScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(navController)
        }

        composable("favorites") {
            FavoritesScreen(navController)
        }
    }
}