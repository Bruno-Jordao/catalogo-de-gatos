package com.example.catalogo_de_gatos.api

import com.example.catalogo_de_gatos.model.Cat
import retrofit2.http.GET

interface CatApi {

    @GET("images/search")
    suspend fun buscarGato(): List<Cat>

}