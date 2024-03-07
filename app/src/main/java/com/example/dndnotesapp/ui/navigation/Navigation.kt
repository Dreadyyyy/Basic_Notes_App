package com.example.dndnotesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dndnotesapp.data.Note
import com.example.dndnotesapp.ui.screens.home.HomeScreen
import com.example.dndnotesapp.ui.screens.note.NoteScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ApplicationScreens.Home.name
    ) {
        composable(route = ApplicationScreens.Home.name) {
            HomeScreen(
                navigateToNote = { id: Int ->
                    navController.navigate("${ApplicationScreens.Note.name}/$id")
                }
            )
        }
        composable(
            route = "${ApplicationScreens.Note.name}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            NoteScreen(
                navigateUp = navController::navigateUp
            )
        }
    }
}