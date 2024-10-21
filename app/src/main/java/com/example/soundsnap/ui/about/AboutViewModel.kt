package com.example.soundsnap.ui.about

import androidx.lifecycle.ViewModel
import com.example.soundsnap.ui.nav.NavEvent
import com.example.soundsnap.ui.nav.Navigator

class AboutViewModel : ViewModel() {
    fun onIntent(intent: AboutIntent) {
        when (intent) {
            AboutIntent.OnBackClick -> onBackClick()
        }
    }

    private fun onBackClick() {
        Navigator.sendEvent(NavEvent.NavigateBack)
    }
}