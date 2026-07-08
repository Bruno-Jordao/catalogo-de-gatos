package com.example.catalogo_de_gatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.catalogo_de_gatos.navigation.Navigation
import com.example.catalogo_de_gatos.ui.theme.CatalogodegatosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatalogodegatosTheme {
                Navigation()
            }
        }
    }
}