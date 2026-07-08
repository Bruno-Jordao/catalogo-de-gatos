package com.example.catalogo_de_gatos.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.catalogo_de_gatos.model.Cat
import com.example.catalogo_de_gatos.model.Favorite
import com.example.catalogo_de_gatos.repository.CatRepository
import com.example.catalogo_de_gatos.repository.FavoriteRepository
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController
) {

    val catRepository = remember {
        CatRepository()
    }

    val favoriteRepository = remember {
        FavoriteRepository()
    }

    var cat by remember {
        mutableStateOf<Cat?>(null)
    }

    var mensagem by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        cat = catRepository.buscarGato()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Catálogo de Gatos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(45.dp))

        cat?.let {

            AsyncImage(
                model = it.url,
                contentDescription = null,
                modifier = Modifier.size(290.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    scope.launch {
                        cat = catRepository.buscarGato()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Buscar outro gato")
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    scope.launch {
                        cat?.let {

                            favoriteRepository.salvarFavorito(
                                Favorite(
                                    catId = it.id,
                                    imageUrl = it.url,
                                    note = "Gato foda!"
                                )
                            )

                            mensagem = "Favorito salvo!"
                            cat = catRepository.buscarGato()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Favoritar")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("favorites")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Ver Favoritos")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(mensagem)
    }
}