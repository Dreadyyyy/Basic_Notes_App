package com.example.dndnotesapp.fake

import com.example.dndnotesapp.data.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object FakeDatasource {
    val note1: Note = Note(
        headline = "Headline1",
        text = "Text1"
    )
    val note2: Note = Note(
        headline = "Headline2",
        text = "Text2"
    )
    val notesList: MutableList<Note> = mutableListOf(
        note1, note2
    )
}