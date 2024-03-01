package com.example.dndnotesapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dndnotesapp.data.NotesRepository
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
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeScreenUiState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}