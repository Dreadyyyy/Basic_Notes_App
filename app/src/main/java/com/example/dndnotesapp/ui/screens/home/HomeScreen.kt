package com.example.dndnotesapp.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dndnotesapp.data.Note

@Composable
fun HomeScreen(
    notes: List<Note>,
    addNewNote: () -> Unit,
    navigateToNote: (Int) -> Unit
) {
    Column {
        Text(text = "This is home screen")
        LazyColumn {
            items(notes, { note: Note -> note.id }) { note: Note ->
                NoteCard(
                    note = note,
                    navigateToNote = navigateToNote
                )
            }
        }
        FloatingActionButton(
            onClick = addNewNote
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add"
            )
        }
    }
}

@Composable
fun NoteCard(
    note: Note,
    navigateToNote: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(onClick = { navigateToNote(note.id) }) {
        Card(
            modifier = modifier
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = note.headline, modifier = Modifier.fillMaxWidth())
                Text(text = note.text, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}