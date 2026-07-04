package com.example.catalogo_de_gatos.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.catalogo_de_gatos.repository.CatRepository
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {

    val repository = remember {

        CatRepository()

    }

    var imagem by remember {

        mutableStateOf("")

    }

    val scope = rememberCoroutineScope()

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {

        Button(

            onClick = {

                scope.launch {

                    imagem = repository
                        .buscarGato()
                        .url

                }

            }

        ) {

            Text("Buscar gato")

        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        if (imagem.isNotEmpty()) {

            AsyncImage(

                model = imagem,

                contentDescription = "Gato",

                modifier = Modifier.size(300.dp)

            )

        }

    }

}