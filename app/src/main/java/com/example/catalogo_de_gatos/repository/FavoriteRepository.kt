package com.example.catalogo_de_gatos.repository;

import com.example.catalogo_de_gatos.api.RetrofitClient;
import com.example.catalogo_de_gatos.model.Favorite;

import java.util.List;

public class FavoriteRepository {

    suspend fun listarFavoritos():List<Favorite>

    {

        return RetrofitClient.favoriteApi.getFavorites()

    }

    suspend fun salvarFavorito(
            favorite: Favorite
    ): Favorite {

        return RetrofitClient.favoriteApi.addFavorite(favorite)

    }

    suspend fun atualizarFavorito(
            favorite: Favorite
    ): Favorite {

        return RetrofitClient.favoriteApi.updateFavorite(

                favorite.id!!,

                favorite

        )

    }

    suspend fun excluirFavorito(
            id: String
    ) {

        RetrofitClient.favoriteApi.deleteFavorite(id)

    }
}