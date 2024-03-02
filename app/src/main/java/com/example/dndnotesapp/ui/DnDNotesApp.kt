package com.example.dndnotesapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dndnotesapp.ui.navigation.Navigation
import com.example.dndnotesapp.ui.screens.home.HomeScreenUiState
import com.example.dndnotesapp.ui.screens.home.HomeScreenViewModel
import com.example.dndnotesapp.ui.screens.note.NoteScreenUiState
import com.example.dndnotesapp.ui.screens.note.NoteScreenViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DnDNotesApp(
    homeScreenViewModel: HomeScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    noteScreenViewModel: NoteScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeScreenUiState by homeScreenViewModel.homeScreenUiState.collectAsState()
    val noteScreenUiState by noteScreenViewModel.noteScreenUiState.collectAsState()
    val navHostController: NavHostController = rememberNavController()
    Navigation(
        navController = navHostController,
        notes = homeScreenUiState.notesList,
        noteScreenUiState = noteScreenUiState,
        onHeadlineChange = noteScreenViewModel::onHeadlineChange,
        onTextChange = noteScreenViewModel::onTextChange
    )
}