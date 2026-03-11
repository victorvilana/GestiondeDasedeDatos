package com.example.gestiondedasededatos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun CurrentRoute(navController: NavController): String? =
    navController.currentBackStackEntryAsState().value?.destination?.route
