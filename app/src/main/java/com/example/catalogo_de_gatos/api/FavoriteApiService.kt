package com.example.catalogo_de_gatos.api

import com.example.catalogo_de_gatos.model.Favorite
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FavoriteApiService {

    @GET("favorites")
    suspend fun getFavorites(): List<Favorite>

    @POST("favorites")
    suspend fun addFavorite(
        @Body favorite: Favorite
    ): Favorite

    @PUT("favorites/{id}")
    suspend fun updateFavorite(
        @Path("id") id: String,
        @Body favorite: Favorite
    ): Favorite

    @DELETE("favorites/{id}")
    suspend fun deleteFavorite(
        @Path("id") id: String
    ): Response<Unit>
}