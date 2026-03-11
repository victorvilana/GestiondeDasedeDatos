package com.example.gestiondedasededatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.gestiondedasededatos.navigation.AppNavigation
import com.example.gestiondedasededatos.screens.CantonesViewModel
import com.example.gestiondedasededatos.screens.HomeScreen
import com.example.gestiondedasededatos.screens.ProvinciasScreen
import com.example.gestiondedasededatos.screens.ProvinciasViewModel
import com.example.gestiondedasededatos.ui.theme.GestiondeDasedeDatosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GestiondeDasedeDatosTheme {

                val provinciasViewModel: ProvinciasViewModel by viewModels()
                val cantonesViewModel: CantonesViewModel by viewModels()

                AppNavigation(
                    provinciasViewModel = provinciasViewModel,
                    cantonesViewModel = cantonesViewModel
                )
            }
        }
    }
}