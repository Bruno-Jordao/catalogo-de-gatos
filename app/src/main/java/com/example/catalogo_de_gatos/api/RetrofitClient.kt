package com.example.catalogo_de_gatos.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // API dos gatos
    private const val CAT_BASE_URL =
        "https://api.thecatapi.com/v1/"

    // MockAPI
    private const val FAVORITE_BASE_URL =
        "https://6a498472a033dcb98d656880.mockapi.io/api/v1/"

    val catApi: CatApi by lazy {

        Retrofit.Builder()
            .baseUrl(CAT_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(CatApi::class.java)
    }

    val favoriteApi: FavoriteApiService by lazy {

        Retrofit.Builder()
            .baseUrl(FAVORITE_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(FavoriteApiService::class.java)

    }
}