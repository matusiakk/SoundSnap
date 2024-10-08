package com.example.soundsnap.ui.start

import androidx.lifecycle.ViewModel
import com.example.soundsnap.ui.nav.NavEvent
import com.example.soundsnap.ui.nav.Navigator
import com.example.soundsnap.ui.nav.Screen

class StartViewModel : ViewModel() {

    fun onIntent(intent: StartIntent) {
        when (intent) {
            is StartIntent.OnGameClick -> onGameClick(intent.category.toString())
        }
    }

    private fun onGameClick(category: String) {
        Navigator.sendEvent(NavEvent.NavigateTo(Screen.GameScreen.withArgs(category)))
    }
}