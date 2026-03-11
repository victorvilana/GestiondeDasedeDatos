package com.example.gestiondedasededatos.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestiondedasededatos.models.Provincia
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ProvinciasScreen(
    navController: NavController,
    onInsertProvincia: (Provincia) -> Unit,
    onDeleteProvincia: (Provincia) -> Unit,
    onGetAllProvincia: () -> Unit,
    provincias: List<Provincia>,
) {

    var codProvincia by remember { mutableStateOf("") }
    var nombreProvincia by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
    ) {
        TopAppBar(
            title = { Text("Provincias") },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                label = { Text(text = "Código de la provincia") },
                value = codProvincia,
                onValueChange = {codProvincia = it},
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                label = { Text(text = "Nombre de la provincia") },
                value = nombreProvincia,
                onValueChange = {nombreProvincia = it},
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (codProvincia.isNotEmpty() && nombreProvincia.isNotEmpty()) {
                        onInsertProvincia(
                            Provincia(
                                cod_provincia = codProvincia,
                                nombre_provincia = nombreProvincia
                            )

                        )
                        codProvincia = ""
                        nombreProvincia = ""
                    } else{
                        Log.d("Provincias", "No se pudo guardar la provincia $codProvincia - $nombreProvincia")
                    }

                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Guardar")
            }

            HorizontalDivider(
                modifier = Modifier.padding(top = 20.dp)
            )

            LazyColumn() {
                items(provincias) { provincia ->
                    ItemProvincia(
                        provincia = provincia,
                        onDelete = { onDeleteProvincia(it) }

                    )
                }


            }

        }

    }

}





@Composable
fun ItemProvincia(
        provincia: Provincia,
        onDelete: (Provincia) -> Unit
) {

    Surface(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        color = Color.LightGray,
        shape = RoundedCornerShape(24.dp)
    )
    {

        Row(
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 6.dp)
                    .weight(1f)

            ) {
                Text(text = provincia.cod_provincia)
                Text(text = provincia.nombre_provincia)
                Text(text = formatoFecha(provincia.fecha.time))

            }
            IconButton(onClick = {onDelete(provincia)}) {
                Icon(Icons.Rounded.Delete, contentDescription = "Eliminar Nota")
            }
        }
    }
}

fun formatoFecha(time : Long) : String {
    val fecha = Date(time)
    val format = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
    return format.format(fecha)
}




