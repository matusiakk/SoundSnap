package com.example.soundsnap.ui.about

import com.example.soundsnap.ui.nav.NavEvent
import com.example.soundsnap.ui.nav.Navigator
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AboutViewModelTest {

    private lateinit var sut: AboutViewModel

    @Test
    fun `should navigate back when back click`() = runTest {
        //Arrange
        setup()
        //Act
        sut.onIntent(AboutIntent.OnBackClick)
        //Assert
        verify(exactly = 1){Navigator.sendEvent(NavEvent.NavigateBack)}

    }

    private fun setup() {
        mockkObject(Navigator)
        sut = AboutViewModel()
    }
}