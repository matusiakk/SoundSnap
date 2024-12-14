package com.example.soundsnap.ui.game

import androidx.lifecycle.SavedStateHandle
import com.example.soundsnap.ui.nav.NavEvent
import com.example.soundsnap.ui.nav.Navigator
import com.example.soundsnap.ui.nav.Screen
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class GameViewModelTest {

    private val savedStateHandle: SavedStateHandle = SavedStateHandle().apply {
        set("category", "ANIMALS")
    }

    private lateinit var sut: GameViewModel
    private val testDispatcher = StandardTestDispatcher()


    @Test
    fun `should initialize game with category from saved state`() = runTest {

        // Arrange
        setup()
        // Act
        // Assert
        assertEquals(Categories.ANIMALS, sut.state.value.category)
    }

    @Test
    fun `should update state with game items after starting game`() = runTest {
        //Arrange
        setup()
        //Act
        //Assert
        assertEquals(1, sut.state.value.firstImage)
        assertEquals(2, sut.state.value.secondImage)
        assertEquals(1, sut.state.value.sound)
    }

    @Test
    fun `should play sound after start game`() = runTest {
        //Arrange
        setup()
        //Act
        //Assert
        assertTrue(sut.state.value.isPlayingSound)
    }

    @Test
    fun `should stop playing sound on timeout`() = runTest {
        //Arrange
        setup()
        //Act
        sut.onIntent(GameIntent.OnTimeout)
        //Assert
        assertFalse(sut.state.value.isPlayingSound)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should update score when click on correct image`() = runTest {
        //Arrange
        setup()
        Dispatchers.setMain(testDispatcher)
        //Act
        sut.onIntent(GameIntent.OnImageClick(1))
        //Assert
        assertEquals(1, sut.state.value.score)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should not update score when click on incorrect image`() = runTest {
        //Arrange
        setup()
        Dispatchers.setMain(testDispatcher)
        //Act
        sut.onIntent(GameIntent.OnImageClick(2))
        //Assert
        assertEquals(0, sut.state.value.score)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should show message after click on image`() = runTest {
        //Arrange
        setup()
        Dispatchers.setMain(testDispatcher)
        //Act
        sut.onIntent(GameIntent.OnImageClick(1))
        //Assert
        assertTrue(sut.state.value.showMessage)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should stop showing message after 1s`() = runTest {
        //Arrange
        setup()
        Dispatchers.setMain(testDispatcher)
        //Act
        sut.onIntent(GameIntent.OnImageClick(1))
        advanceTimeBy(1000)
        //Assert
        assertTrue(sut.state.value.showMessage)
    }

    @Test
    fun `should navigate to ResultScreen when time out`() = runTest {
        //Arrange
        val initialState = GameState(score = 12)
        setup(initialState)
        //Act
        sut.onIntent(GameIntent.OnTimeout)
        //Assert
        verify(exactly = 1) {
            Navigator.sendEvent(
                NavEvent.NavigateTo(
                    Screen.ResultsScreen.withArgs(
                        "12"
                    )
                )
            )
        }
    }

    @Test
    fun `should navigate back after back click`() = runTest {
        //Arrange
        setup()
        //Act
        sut.onIntent(GameIntent.OnBackClick)
        //Assert
        verify(exactly = 1) { Navigator.sendEvent(NavEvent.NavigateBack) }
    }


    private fun setup(initialState: GameState = GameState(score = 0)) {
        mockkObject(GameItem)
        every { GameItem.getRandomItems(Categories.ANIMALS) } returns Triple(1, 2, 1)
        mockkObject(Navigator)

        sut = GameViewModel(
            initialState = initialState,
            savedStateHandle = savedStateHandle
        )
    }

}