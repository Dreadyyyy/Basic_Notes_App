package com.example.dndnotesapp.fake

import android.util.Log
import com.example.dndnotesapp.data.Note
import com.example.dndnotesapp.data.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val TAG: String = "FakeNotesRepository"
class FakeNotesRepository : NotesRepository {
    val note0: Note = Note(id = 0, headline = "Headline0", text = "Text0")
    val note1: Note = Note(id = 1, headline = "Headline1", text = "Text1")
    private val notesList: MutableList<Note> = mutableListOf(
        note0, note1
    )
    override suspend fun insertNote(note: Note) {
        notesList.add(note.copy(id = notesList.size))
    }

    override suspend fun updateNote(note: Note) {
        try {
            notesList[note.id] = note
        } catch (exception: IndexOutOfBoundsException) {
            Log.e(TAG, exception.message ?: "Unknown error")
        }
    }

    override suspend fun deleteNote(note: Note) {
        try {
            notesList.removeAt(note.id)
            (note.id..notesList.lastIndex).forEach {index: Int->
                notesList[index] = notesList[index].copy(id = index)
            }
        } catch (exception: IndexOutOfBoundsException) {
            Log.e(TAG, exception.message ?: "Unknown error")
        }
    }

    override fun getAllNotes(): Flow<List<Note>> =
        flow {
            while (true) emit(notesList)
        }

    override fun getNote(id: Int): Flow<Note?> = flow {
        while (true) emit(notesList.getOrNull(id))
    }
}