package com.example.dndnotesapp.ui.screens.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dndnotesapp.ui.AppViewModelProvider
import com.example.dndnotesapp.ui.theme.DnDNotesAppTheme

@Composable
fun NoteScreen(
    noteScreenViewModel: NoteScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateUp: () -> Unit
) {
    val customNavigateUp: () -> Unit = {
        navigateUp()
        noteScreenViewModel.updateNote()
    }
    BackHandler {
        customNavigateUp()
    }
    val noteScreenUiState: NoteScreenUiState = noteScreenViewModel.noteScreenUiState
    val showingDeleteDialog: Boolean = noteScreenUiState.showingDeleteDialog
    when {
        showingDeleteDialog -> {
            DeleteDialog(
                onDismissRequest = noteScreenViewModel::hideDeleteDialog,
                deleteNote = {
                    noteScreenViewModel.hideDeleteDialog()
                    navigateUp()
                    noteScreenViewModel.deleteNote()
                }
            )
        }
    }
    NoteScreenContent(
        noteScreenUiState = noteScreenUiState,
        navigateUp = customNavigateUp,
        updateHeadline = noteScreenViewModel::updateHeadline,
        updateText = noteScreenViewModel::updateText,
        showDeleteDialog = noteScreenViewModel::showDeleteDialog
    )
}

@Composable
fun NoteScreenContent(
    noteScreenUiState: NoteScreenUiState,
    navigateUp: () -> Unit,
    updateHeadline: (String) -> Unit,
    updateText: (String) -> Unit,
    showDeleteDialog: () -> Unit
) {
    Scaffold(
        topBar = {
            NoteScreenTopBar(
                navigateUp = navigateUp,
                showDeleteDialog = showDeleteDialog
            )
        }
    ) { innerPadding: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TextField(
                value = noteScreenUiState.noteDetails.headline,
                onValueChange = updateHeadline,
                placeholder = { Text(text = "Headline") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = noteScreenUiState.noteDetails.text,
                onValueChange = updateText,
                placeholder = { Text(text = "Text") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreenTopBar(
    navigateUp: () -> Unit,
    showDeleteDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = "Edit")
        },
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(onClick = showDeleteDialog) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }
        },
        modifier = modifier
    )
}

@Composable
fun DeleteDialog(
    onDismissRequest: () -> Unit,
    deleteNote: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
        },
        text = {
            Text(text = "I you sure you want to delete this note")
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = deleteNote) {
                Text(text = "Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = "Cancel")
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteScreenPreview(
    noteScreenUiState: NoteScreenUiState = NoteScreenUiState(
        NoteDetails()
    )
) {
    DnDNotesAppTheme {
        NoteScreenContent(
            noteScreenUiState = noteScreenUiState,
            navigateUp = {},
            updateHeadline = {},
            updateText = {},
            showDeleteDialog = {}
        )
    }
}
