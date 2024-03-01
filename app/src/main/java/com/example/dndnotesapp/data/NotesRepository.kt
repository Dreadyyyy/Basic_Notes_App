package com.example.dndnotesapp.data

import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    fun getAllNotes(): Flow<List<Note>>
    fun getNote(id: Int): Flow<Note?>
}