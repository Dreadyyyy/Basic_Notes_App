package com.example.dndnotesapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dndnotesapp.data.Note
import com.example.dndnotesapp.ui.navigation.Navigation
import com.example.dndnotesapp.ui.screens.home.HomeScreenUiState
import com.example.dndnotesapp.ui.screens.home.HomeScreenViewModel
import com.example.dndnotesapp.ui.screens.note.NoteScreenUiState
import com.example.dndnotesapp.ui.screens.note.NoteScreenViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DnDNotesApp() {
    val navHostController: NavHostController = rememberNavController()
    Navigation(
        navController = navHostController
    )
}