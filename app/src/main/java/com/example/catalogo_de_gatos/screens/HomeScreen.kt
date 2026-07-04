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
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = "Catálogo de Gatos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        cat?.let {

            AsyncImage(
                model = it.url,
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(

                onClick = {

                    scope.launch {

                        cat = catRepository.buscarGato()

                    }


                }

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

                                    note = "Muito fofo"

                                )

                            )

                            mensagem = "Favorito salvo!"

                        }

                    }

                }

            ) {

                Text("Favoritar")

            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = {

                navController.navigate("favorites")

            }

        ) {

            Text("Ver Favoritos")

        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(mensagem)

    }

}