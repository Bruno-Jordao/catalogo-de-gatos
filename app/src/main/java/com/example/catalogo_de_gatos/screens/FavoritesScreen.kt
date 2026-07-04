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

    val repository = remember {

        FavoriteRepository()

    }

    var favorites by remember {

        mutableStateOf<List<Favorite>>(emptyList())

    }

    //val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {

        favorites = repository.listarFavoritos()

    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        Text(

            text = "Favoritos",

            style = MaterialTheme.typography.headlineMedium

        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(favorites) { favorite ->

                FavoriteItem(favorite)

            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = {

                navController.popBackStack()

            }

        ) {

            Text("Voltar")

        }

    }

}
@Composable
fun FavoriteItem(

    favorite: Favorite

) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)

    ) {

        Column(

            modifier = Modifier.padding(12.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            AsyncImage(

                model = favorite.imageUrl,

                contentDescription = null,

                modifier = Modifier.size(220.dp)

            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(

                text = favorite.note

            )

        }

    }

}