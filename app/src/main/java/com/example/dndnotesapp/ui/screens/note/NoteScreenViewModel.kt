package com.example.dndnotesapp.ui.screens.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dndnotesapp.data.NotesRepository
import com.example.dndnotesapp.ui.AppViewModelProvider
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class NoteScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository
) : ViewModel() {
    private val id: Int = savedStateHandle["id"] ?: 0
    val noteScreenUiState: StateFlow<NoteScreenUiState> =
        notesRepository.getNote(id)
            .filterNotNull()
            .map {
                NoteScreenUiState(
                    noteDetails = it.toNoteDetails()
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(AppViewModelProvider.TIMEOUT_MILLIS),
                initialValue = NoteScreenUiState(
                    noteDetails = NoteDetails()
                )
            )
    fun onHeadlineChange(newHeadline: String) {
        TODO()
    }
    fun onTextChange(newText: String) {
        TODO()
    }
}