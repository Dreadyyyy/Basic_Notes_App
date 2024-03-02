package com.example.dndnotesapp.ui.screens.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteScreen(
    noteScreenUiState: NoteScreenUiState,
    onHeadlineChange: (String) -> Unit,
    onTextChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = noteScreenUiState.noteDetails.headline,
            onValueChange = onHeadlineChange,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = noteScreenUiState.noteDetails.text,
            onValueChange = onTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
        )
    }
}