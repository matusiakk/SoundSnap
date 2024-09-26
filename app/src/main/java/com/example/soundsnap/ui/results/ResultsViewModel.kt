package com.example.soundsnap.ui.results

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.soundsnap.ui.nav.NavEvent
import com.example.soundsnap.ui.nav.Navigator
import com.example.soundsnap.ui.nav.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ResultsState())
    val state: StateFlow<ResultsState> = _state

    init {
        showResult(savedStateHandle.get<String>("score")!!.toInt())
    }

    private fun showResult(score: Int) {
        _state.update { it.copy(score = score) }
    }

    fun onIntent(intent: ResultsIntent) {
        when (intent) {
            is ResultsIntent.OnAgainClick -> onAgainClick()
        }
    }

    private fun onAgainClick() {
        Navigator.sendEvent(NavEvent.NavigateTo(Screen.GameScreen.route))
    }
}