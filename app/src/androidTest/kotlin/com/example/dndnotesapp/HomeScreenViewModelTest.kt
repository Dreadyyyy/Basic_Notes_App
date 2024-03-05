package com.example.dndnotesapp

import com.example.dndnotesapp.data.Note
import com.example.dndnotesapp.fake.FakeDatasource
import com.example.dndnotesapp.fake.FakeOfflineNotesRepository
import com.example.dndnotesapp.rule.TestDispatcherRule
import com.example.dndnotesapp.ui.screens.home.HomeScreenUiState
import com.example.dndnotesapp.ui.screens.home.HomeScreenViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HomeScreenViewModelTest {
    @get: Rule
    val testDispatcher: TestDispatcherRule = TestDispatcherRule()
    val homeScreenViewModel: HomeScreenViewModel = HomeScreenViewModel(
        FakeOfflineNotesRepository()
    )
    @Test
    fun homeScreenViewModel_homeScreenEntered_notesInList() {
        val uiState: HomeScreenUiState = homeScreenViewModel.homeScreenUiState.value
        assertEquals(FakeDatasource.note1, uiState.notesList[0])
        assertEquals(FakeDatasource.note2, uiState.notesList[1])
    }
}