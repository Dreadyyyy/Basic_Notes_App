package com.example.dndnotesapp.ui.screens.note

import com.example.dndnotesapp.data.Note

data class NoteScreenUiState(
    val noteDetails: NoteDetails,
    val showingDeleteDialog: Boolean = false
)

data class NoteDetails(
    val id: Int = 0,
    val headline: String = "",
    val text: String = ""
)

fun Note.toNoteDetails(): NoteDetails = NoteDetails(
    id = this.id,
    headline = this.headline,
    text = this.text
)

fun NoteDetails.toNote(): Note = Note(
    id = this.id,
    headline = this.headline,
    text = this.text
)