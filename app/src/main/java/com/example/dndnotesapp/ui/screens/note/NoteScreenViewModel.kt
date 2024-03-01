package com.example.dndnotesapp.ui.screens.note

import androidx.lifecycle.ViewModel
import com.example.dndnotesapp.data.NotesRepository
import kotlinx.coroutines.flow.StateFlow

class NoteScreenViewModel(
    private val notesRepository: NotesRepository
): ViewModel() {
    val noteScreenUiState: StateFlow<NoteScreenUiState> = TODO()
}