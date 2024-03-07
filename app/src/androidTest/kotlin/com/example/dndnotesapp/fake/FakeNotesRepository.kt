package com.example.dndnotesapp.fake

import com.example.dndnotesapp.data.Note
import com.example.dndnotesapp.data.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNotesRepository : NotesRepository {
    val note1: Note = Note(id = 0, headline = "Headline1", text = "Text1")
    val note2: Note = Note(id = 1, headline = "Headline2", text = "Text2")
    override suspend fun insertNote(note: Note) {
    }

    override suspend fun updateNote(note: Note) {
    }

    override suspend fun deleteNote(note: Note) {
    }

    override fun getAllNotes(): Flow<List<Note>> =
        flow {
            emit(
                listOf(note1, note2)
            )
        }

    override fun getNote(id: Int): Flow<Note?> = flow {
        emit(note1)
    }
}