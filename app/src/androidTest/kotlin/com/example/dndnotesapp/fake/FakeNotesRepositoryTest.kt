package com.example.dndnotesapp.fake

import com.example.dndnotesapp.data.Note
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FakeNotesRepositoryTest {
    private val fakeNotesRepository: FakeNotesRepository = FakeNotesRepository()
    @Test
    fun fakeNotesRepository_getAllNotesCalled_flowWithListOfTwoNotesReturned() = runTest{
        val notesList: List<Note> = fakeNotesRepository.getAllNotes().first()
        assertEquals(2, notesList.size)
    }
}