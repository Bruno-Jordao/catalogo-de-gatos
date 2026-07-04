package com.example.catalogo_de_gatos.model

data class Favorite(
    val id: String? = null,
    val catId: String,
    val imageUrl: String,
    val note: String
)