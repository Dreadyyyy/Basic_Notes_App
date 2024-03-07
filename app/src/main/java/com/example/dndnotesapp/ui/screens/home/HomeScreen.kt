package com.example.dndnotesapp.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dndnotesapp.data.Note
import com.example.dndnotesapp.ui.AppViewModelProvider
import com.example.dndnotesapp.ui.theme.DnDNotesAppTheme

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToNote: (Int) -> Unit
) {
    val homeScreenUiState = homeScreenViewModel.homeScreenUiState.collectAsState(initial = HomeScreenUiState())
    val notes: List<Note> = homeScreenUiState.value.notesList
    Scaffold(
        topBar = {
            HomeScreenTopBar()
        }
    ) { innerPadding: PaddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(notes, { note: Note -> note.id }) { note: Note ->
                    NoteCard(
                        note = note,
                        navigateToNote = navigateToNote
                    )
                }
            }
            FloatingActionButton(
                onClick = { homeScreenViewModel.addNewNote(Note(headline = "", text = "")) },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add"
                )
            }
        }
    }
}

@Composable
fun NoteCard(
    note: Note,
    navigateToNote: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { navigateToNote(note.id) }
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(120.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            Text(text = note.headline, modifier = Modifier.fillMaxWidth())
            Text(text = note.text, modifier = Modifier.fillMaxWidth())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = "Notes")
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    DnDNotesAppTheme {
        HomeScreen(
            navigateToNote = { _ -> }
        )
    }
}