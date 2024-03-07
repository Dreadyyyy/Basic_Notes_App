package com.example.dndnotesapp

import com.example.dndnotesapp.fake.FakeNotesRepository
import com.example.dndnotesapp.ui.screens.home.HomeScreenUiState
import com.example.dndnotesapp.ui.screens.home.HomeScreenViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HomeScreenViewModelTest {
    private val fakeNotesRepository: FakeNotesRepository = FakeNotesRepository()
    private val homeScreenViewModel: HomeScreenViewModel = HomeScreenViewModel(fakeNotesRepository)
    @Test
    fun homeScreenViewModel_dataFetched_notesListContainsNotes() = runTest {
        val uiState: Flow<HomeScreenUiState> = homeScreenViewModel.homeScreenUiState
        assertEquals(2, uiState.first().notesList.size)
    }
}