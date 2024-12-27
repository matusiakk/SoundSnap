package com.example.soundsnap.ui.start

import com.example.soundsnap.ui.game.Categories
import com.example.soundsnap.ui.nav.NavEvent
import com.example.soundsnap.ui.nav.Navigator
import com.example.soundsnap.ui.nav.Screen
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StartViewModelTest {

    private lateinit var sut: StartViewModel

    @Test
    fun `should navigate to about screen when about click`() = runTest {
        //Arrange
        setup()
        //Act
        sut.onIntent(StartIntent.OnAboutClick)
        //Assert
        verify(exactly = 1) { Navigator.sendEvent(NavEvent.NavigateTo(Screen.AboutScreen.route)) }
    }

    @Test
    fun `should show menu when options click`() = runTest {
        //Arrange
        setup()
        //Act
        sut.onIntent(StartIntent.OnOptionsClick)
        //Assert
        assertTrue(sut.state.value.showMenu)
    }

    @Test
    fun `should hide menu when options dismiss`() = runTest {
        //Arrange
        setup()
        //Act
        sut.onIntent(StartIntent.OnOptionsDismiss)
        //Assert
        assertFalse(sut.state.value.showMenu)
    }

    @Test
    fun `should navigate to game screen with selected category when game click`() = runTest {
        //Arrange
        setup()
        //Act
        sut.onIntent(StartIntent.OnGameClick(Categories.ANIMALS))
        //Assert
        verify(exactly = 1) { Navigator.sendEvent(NavEvent.NavigateTo(Screen.GameScreen.withArgs("ANIMALS"))) }
    }


    private fun setup() {
        mockkObject(Navigator)
        sut = StartViewModel()
    }
}