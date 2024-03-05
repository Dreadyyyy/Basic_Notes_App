package com.example.dndnotesapp.fake

import com.example.dndnotesapp.data.Note
import com.example.dndnotesapp.data.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking

class FakeOfflineNotesRepository : NotesRepository {
    override fun getAllNotes(): Flow<List<Note>> =
        MutableStateFlow(FakeDatasource.notesList)

    override fun getNote(id: Int): Flow<Note> = runBlocking {
        MutableStateFlow(FakeDatasource.notesList[id])
    }

    override suspend fun insertNote(note: Note) {
        TODO()
    }

    override suspend fun updateNote(note: Note) = runBlocking {
        TODO()
    }

    override suspend fun deleteNote(note: Note) {
        TODO()
    }
}