package com.example.gestiondedasededatos.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable


fun HomeScreen(
    onGoToProvincias: () -> Unit,
    onGoToCantones: () -> Unit
) {

    Column(
        modifier = Modifier
    ) {
        TopAppBar(
            title = {
                Text(text = "Seleccione la acción a realizar")
            },
            actions = {
                Icon(
                    imageVector = Icons.Default.AddLocationAlt,
                    contentDescription = "Seleccione la acción a realizar"

                )
            }
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Button(onClick = onGoToProvincias) {
                Text(text = "Insertar Provincia")
            }

            Button(onClick = onGoToCantones) {
                Text(text = "Insertar Cantón")
            }

        }


    }
}

