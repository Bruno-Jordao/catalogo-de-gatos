package com.example.catalogo_de_gatos.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.catalogo_de_gatos.model.Favorite
import com.example.catalogo_de_gatos.repository.FavoriteRepository
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(
    navController: NavController
) {

    val repository = remember { FavoriteRepository() }
    val scope = rememberCoroutineScope()

    var favorites by remember {
        mutableStateOf<List<Favorite>>(emptyList())
    }

    fun atualizarLista() {
        scope.launch {
            favorites = repository.listarFavoritos()
        }
    }

    LaunchedEffect(Unit) {
        atualizarLista()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Favoritos",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(favorites) { favorite ->

                    FavoriteCard(
                        favorite = favorite,
                        repository = repository,
                        atualizarLista = {
                            atualizarLista()
                        }
                    )
                }
            }
        }

        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 38.dp)
                .fillMaxWidth(0.6f)
                .height(50.dp)
        ) {
            Text("Voltar")
        }
    }
}

@Composable
fun FavoriteCard(

    favorite: Favorite,

    repository: FavoriteRepository,

    atualizarLista: () -> Unit

) {

    var observacao by remember {
        mutableStateOf(favorite.note)
    }

    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(330.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            AsyncImage(
                model = favorite.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(160.dp)
            )

            OutlinedTextField(
                value = observacao,
                onValueChange = {
                    observacao = it
                },
                label = {
                    Text("Observação")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                minLines = 2,
                maxLines = 2
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Button(
                    onClick = {
                        scope.launch {

                            repository.atualizarFavorito(
                                Favorite(
                                    id = favorite.id,
                                    catId = favorite.catId,
                                    imageUrl = favorite.imageUrl,
                                    note = observacao
                                )
                            )

                            atualizarLista()
                        }
                    },
                    modifier = Modifier.width(120.dp)
                ) {
                    Text("Salvar")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        scope.launch {
                            repository.excluirFavorito(favorite.id!!)
                            atualizarLista()
                        }
                    },
                    modifier = Modifier.width(120.dp)
                ) {
                    Text("Excluir")
                }
            }
        }
    }
}