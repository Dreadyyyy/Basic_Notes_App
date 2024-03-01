package com.example.dndnotesapp.data

import kotlinx.coroutines.flow.Flow

class OfflineNotesRepository(
    private val notesDao: NotesDao
) : NotesRepository {
    override suspend fun insertNote(note: Note) = notesDao.insertNote(note)

    override suspend fun updateNote(note: Note) = notesDao.updateNote(note)

    override suspend fun deleteNote(note: Note) = notesDao.deleteNote(note)

    override fun getAllNotes(): Flow<List<Note>> = notesDao.getAllNotes()

    override fun getNote(id: Int): Flow<Note?> = notesDao.getNote(id)
}