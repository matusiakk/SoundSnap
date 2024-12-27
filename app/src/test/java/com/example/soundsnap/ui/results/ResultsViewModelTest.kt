package com.example.soundsnap.ui.results

import androidx.lifecycle.SavedStateHandle
import com.example.soundsnap.ui.nav.NavEvent
import com.example.soundsnap.ui.nav.Navigator
import com.example.soundsnap.ui.nav.Screen
import io.mockk.mockkObject
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ResultsViewModelTest {

    private lateinit var sut: ResultsViewModel
    private val savedStateHandle = SavedStateHandle().apply {
        set("score", "12")
    }

    @Test
    fun `should initialize screen with score from saved state`() = runTest {
        //Arrange
        setup()
        //Act
        //Assert
        assertEquals(12, sut.state.value.score)
    }

    @Test
    fun `should navigate to start screen when again click`() = runTest {
        //Arrange
        setup()
        //Act
        sut.onIntent(ResultsIntent.OnAgainClick)
        //Assert
        verify(exactly = 1) { Navigator.sendEvent(NavEvent.NavigateTo(Screen.StartScreen.route)) }
    }

    private fun setup() {
        mockkObject(Navigator)

        sut = ResultsViewModel(savedStateHandle = savedStateHandle)
    }
}