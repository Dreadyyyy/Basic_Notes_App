package com.example.dndnotesapp.fake

import com.example.dndnotesapp.data.Note
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FakeNotesRepositoryTest {
    private val fakeNotesRepository: FakeNotesRepository = FakeNotesRepository()

    @Test
    fun fakeNotesRepository_getAllNotesCalled_flowWithListOfTwoNotesReturned() = runTest {
        val notesList: List<Note> = fakeNotesRepository.getAllNotes().first()
        assertEquals(2, notesList.size)
    }

    @Test
    fun fakeNotesRepository_getNoteWithIndexOneCalled_rightNoteReturned() = runTest {
        val note: Note? = fakeNotesRepository.getNote(0).first()
        assertEquals(fakeNotesRepository.note0, note)
    }

    @Test
    fun fakeNotesRepository_insertNote2_noteInsertedSuccessfully() = runTest {
        insertOneNote()
        val note: Note? = fakeNotesRepository.getNote(2).first()
        assertEquals(note2, note)
    }

    @Test
    fun fakeNotesRepository_insertedTwoNotesAndOneUpdated_fakeRepositoryReturnsCorrectList() =
        runTest {
            insertTwoNotes()
            fakeNotesRepository.updateNote(updatedNote2)
            val expectedList: List<Note> = listOf(
                fakeNotesRepository.note0, fakeNotesRepository.note1,
                updatedNote2, note3
            )
            val noteList: List<Note> = fakeNotesRepository.getAllNotes().first()
            assertEquals(expectedList, noteList)
        }

    @Test
    fun fakeNotesRepository_twoNotesAddedOneDeleted_fakeRepositoryReturnsCorrectIndexedList() = runTest {
            insertTwoNotes()
            fakeNotesRepository.deleteNote(note2)
            val expectedList: List<Note> = listOf(
                fakeNotesRepository.note0, fakeNotesRepository.note1,
                reIndexedNote3
            )
            val noteList: List<Note> = fakeNotesRepository.getAllNotes().first()
            assertEquals(expectedList, noteList)
        }

    private suspend fun insertOneNote() = fakeNotesRepository.insertNote(note2)
    private suspend fun insertTwoNotes() {
        fakeNotesRepository.insertNote(note2)
        fakeNotesRepository.insertNote(note3)
    }

    private companion object {
        private val note2: Note = Note(id = 2, headline = "headline2", text = "text2")
        private val note3: Note = Note(id = 3, headline = "headline3", text = "text3")
        private val updatedNote2 = Note(id = 2, headline = "Headline2", text = "Text2")
        private val reIndexedNote3: Note = Note(id = 2, headline = "headline3", text = "text3")
    }
}