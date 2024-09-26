package com.example.soundsnap.ui.start

import androidx.lifecycle.ViewModel
import com.example.soundsnap.ui.nav.NavEvent
import com.example.soundsnap.ui.nav.Navigator
import com.example.soundsnap.ui.nav.Screen

class StartViewModel : ViewModel() {

    fun onIntent(intent: StartIntent) {
        when (intent) {
            is StartIntent.OnGameClick -> onGameClick()
        }
    }

    private fun onGameClick() {
        Navigator.sendEvent(NavEvent.NavigateTo(Screen.GameScreen.route))
    }
}