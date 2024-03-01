package com.example.dndnotesapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dndnotesapp.NotesApplication
import com.example.dndnotesapp.ui.screens.home.HomeScreenViewModel
import com.example.dndnotesapp.ui.screens.note.NoteScreenViewModel

class AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeScreenViewModel(notesApplication().container.notesRepository)
        }
        initializer {
            NoteScreenViewModel(notesApplication().container.notesRepository)
        }
    }
}

fun CreationExtras.notesApplication(): NotesApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NotesApplication)