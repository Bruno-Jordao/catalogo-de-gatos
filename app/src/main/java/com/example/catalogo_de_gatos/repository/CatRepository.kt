package com.example.catalogo_de_gatos.repository

import com.example.catalogo_de_gatos.api.RetrofitClient
import com.example.catalogo_de_gatos.model.Cat

class CatRepository {

    suspend fun buscarGato(): Cat {

        return RetrofitClient
            .api
            .buscarGato()[0]

    }

}