package com.example.soundsnap.ui.start

import androidx.lifecycle.ViewModel
import com.example.soundsnap.ui.nav.NavEvent
import com.example.soundsnap.ui.nav.Navigator
import com.example.soundsnap.ui.nav.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class StartViewModel : ViewModel() {
    private val _state = MutableStateFlow(StartState())
    val state: StateFlow<StartState> = _state

    fun onIntent(intent: StartIntent) {
        when (intent) {
            is StartIntent.OnGameClick -> onGameClick(intent.category.toString())
            is StartIntent.OnOptionsClick -> onOptionsClick()
            is StartIntent.OnOptionsDismiss -> onOptionsDismiss()
            is StartIntent.OnAboutClick -> onAboutClick()
        }
    }

    private fun onAboutClick() {
        _state.update {
            it.copy(
                showMenu = false
            )
        }
        Navigator.sendEvent(NavEvent.NavigateTo(Screen.AboutScreen.route))
    }

    private fun onOptionsDismiss() {
        _state.update {
            it.copy(
                showMenu = false
            )
        }
    }

    private fun onOptionsClick() {
        _state.update {
            it.copy(
                showMenu = true
            )
        }
    }

    private fun onGameClick(category: String) {
        Navigator.sendEvent(NavEvent.NavigateTo(Screen.GameScreen.withArgs(category)))
    }
}