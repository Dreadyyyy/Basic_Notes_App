package com.example.dndnotesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        private var Instance: NotesDatabase? = null
        fun getDatabase(context: Context): NotesDatabase =
            Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    NotesDatabase::class.java,
                    "notes_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
    }
}