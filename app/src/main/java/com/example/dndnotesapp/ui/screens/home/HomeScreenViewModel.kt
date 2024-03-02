package com.example.dndnotesapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dndnotesapp.data.NotesRepository
import com.example.dndnotesapp.ui.AppViewModelProvider
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeScreenViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {
    val homeScreenUiState: StateFlow<HomeScreenUiState> =
        notesRepository.getAllNotes()
            .map { HomeScreenUiState(it) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(AppViewModelProvider.TIMEOUT_MILLIS),
                initialValue = HomeScreenUiState()
            )
}