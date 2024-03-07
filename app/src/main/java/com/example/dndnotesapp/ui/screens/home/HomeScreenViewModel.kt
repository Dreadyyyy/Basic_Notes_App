package com.example.dndnotesapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dndnotesapp.data.Note
import com.example.dndnotesapp.data.NotesRepository
import com.example.dndnotesapp.ui.AppViewModelProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {
    val homeScreenUiState: Flow<HomeScreenUiState> =
        notesRepository.getAllNotes()
            .map { HomeScreenUiState(it) }
    fun addNewNote(note: Note) {
        viewModelScope.launch {
            notesRepository.insertNote(note)
        }
    }
}