package com.example.dndnotesapp.ui.screens.note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dndnotesapp.data.NotesRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class NoteScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository
) : ViewModel() {
    private val id: Int = checkNotNull(savedStateHandle["id"])
    var noteScreenUiState by mutableStateOf(
        NoteScreenUiState(
            NoteDetails()
        )
    )
        private set

    init {
        viewModelScope.launch {
            noteScreenUiState = NoteScreenUiState(
                notesRepository.getNote(id)
                    .filterNotNull()
                    .first()
                    .toNoteDetails()
            )
        }
    }

    fun updateNote() {
        viewModelScope.launch {
            notesRepository.updateNote(
                noteScreenUiState.noteDetails.toNote()
            )
        }
    }

    fun updateHeadline(newHeadline: String) {
        noteScreenUiState = NoteScreenUiState(
            noteDetails = noteScreenUiState.noteDetails.copy(
                headline = newHeadline
            )
        )
    }

    fun updateText(newText: String) {
        noteScreenUiState = NoteScreenUiState(
            noteDetails = noteScreenUiState.noteDetails.copy(
                text = newText
            )
        )
    }
}