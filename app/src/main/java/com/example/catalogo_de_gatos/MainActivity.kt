package com.example.catalogo_de_gatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.catalogo_de_gatos.screens.FavoritesScreen
import com.example.catalogo_de_gatos.screens.HomeScreen
import com.example.catalogo_de_gatos.ui.theme.CatalogodegatosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            CatalogodegatosTheme {

                AppNavigation()

            }

        }

    }

}

@Composable
fun AppNavigation() {

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