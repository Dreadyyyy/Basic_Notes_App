package com.example.dndnotesapp.ui.screens.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dndnotesapp.ui.AppViewModelProvider

@Composable
fun NoteScreen(
    noteScreenViewModel: NoteScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateUp: () -> Unit
) {
    BackHandler {
        noteScreenViewModel.updateNote()
        navigateUp()
    }
    Scaffold(
        topBar = {
            NoteScreenTopBar(
                navigateUp = {
                    noteScreenViewModel.updateNote()
                    navigateUp()
                }
            )
        }
    ) { innerPadding: PaddingValues ->
        val noteScreenUiState: NoteScreenUiState = noteScreenViewModel.noteScreenUiState
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TextField(
                value = noteScreenUiState.noteDetails.headline,
                onValueChange = noteScreenViewModel::updateHeadline,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = noteScreenUiState.noteDetails.text,
                onValueChange = noteScreenViewModel::updateText,
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
        modifier = modifier
    )
}