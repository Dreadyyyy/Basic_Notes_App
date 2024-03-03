import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dndnotesapp.data.Note
import com.example.dndnotesapp.data.NotesDao
import com.example.dndnotesapp.data.NotesDatabase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class NotesDaoTest {
    private lateinit var notesDao: NotesDao
    private lateinit var notesDatabase: NotesDatabase
    private val note1: Note = Note(1, "NoteOne", "SomeText")
    private val note2: Note = Note(2, "NoteTwo", "AnyText")

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        notesDatabase = Room.inMemoryDatabaseBuilder(context, NotesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        notesDao = notesDatabase.notesDao()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsNoteIntoDb() = runBlocking {
        addOneNote()
        val notes: List<Note> = notesDao.getAllNotes().first()
        assertEquals(1, notes.size)
        assertEquals(note1, notes.first())
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsTwoNotesIntoDb() = runBlocking {
        addTwoNotes()
        val notes: List<Note> = notesDao.getAllNotes().first()
        assertEquals(2, notes.size)
        assertEquals(note1, notes.first())
        assertEquals(note2, notes.last())
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        notesDatabase.close()
    }

    private suspend fun addOneNote() {
        notesDao.insertNote(note1)
    }

    private suspend fun addTwoNotes() {
        notesDao.insertNote(note1)
        notesDao.insertNote(note2)
    }
}