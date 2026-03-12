package com.example.gestiondedasededatos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestiondedasededatos.screens.CantonesScreen
import com.example.gestiondedasededatos.screens.CantonesViewModel
import com.example.gestiondedasededatos.screens.HomeScreen
import com.example.gestiondedasededatos.screens.LoginScreen
import com.example.gestiondedasededatos.screens.ProvinciasScreen
import com.example.gestiondedasededatos.screens.ProvinciasViewModel


@Composable
fun AppNavigation(
    //modifier: Modifier = Modifier,
    //navController: NavController,
    provinciasViewModel: ProvinciasViewModel,
    cantonesViewModel: CantonesViewModel
) {

    val navController = rememberNavController()
    val listaProvincias = provinciasViewModel.provinciasList.collectAsState().value
    val listaCantones = cantonesViewModel.cantonesList.collectAsState().value
    val errorMsgCantones by cantonesViewModel.error.collectAsState()
    val errorMsgProvincias by provinciasViewModel.error.collectAsState()

    NavHost(
        navController = navController,
        startDestination = NavScreen.HomeScreen.name,
        //modifier = modifier
    ) {
        composable(NavScreen.LoginScreen.name) {
            LoginScreen(navController = navController)
        }

        composable(NavScreen.HomeScreen.name) {
            HomeScreen(
                onGoToProvincias = { navController.navigate(NavScreen.ProvinciasScreen.name) },
                onGoToCantones = { navController.navigate(NavScreen.CantonesScreen.name) },
            )
        }

        composable(NavScreen.ProvinciasScreen.name) {
            ProvinciasScreen(
                navController = navController,
                provincias = listaProvincias,
                onInsertProvincia = { provinciasViewModel.insertProvincias(it) },
                onDeleteProvincia = { provinciasViewModel.deleteProvincias(it) },
                onGetAllProvincia = { provinciasViewModel.getAllProvincias() },
                errorMessage = errorMsgProvincias
            )
        }

        composable(NavScreen.CantonesScreen.name) {
            CantonesScreen(
                navController = navController,
                cantones = listaCantones,
                provincias = listaProvincias,
                onInsertCanton = { cantonesViewModel.insertCantones(it) },
                onDeleteCanton = { cantonesViewModel.deleteCantones(it) },
                onGetAllCanton = { cantonesViewModel.getAllCantones() },
                errorMessage = errorMsgCantones
            )
        }


    }
}
