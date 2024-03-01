package com.example.dndnotesapp.ui.screens.home

import com.example.dndnotesapp.data.Note

data class HomeScreenUiState(
    val notesList: List<Note> = listOf()
)
