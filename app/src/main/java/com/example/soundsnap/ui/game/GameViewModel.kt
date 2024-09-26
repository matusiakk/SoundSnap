package com.example.soundsnap.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundsnap.ui.nav.NavEvent
import com.example.soundsnap.ui.nav.Navigator
import com.example.soundsnap.ui.nav.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(GameState())
    val state: StateFlow<GameState> = _state

    init {
        startGame()
    }

    private fun startGame() {
        _state.update {
            it.copy(
                isLoading = true,
                showMessage = false,
                isClickable = true
            )
        }
        drawGame()
    }

    private fun drawGame() {
        val firstImageIndex = Random.nextInt(_state.value.images.size)
        var secondImageIndex = Random.nextInt(_state.value.images.size)
        while (firstImageIndex == secondImageIndex)
            secondImageIndex = Random.nextInt(_state.value.images.size)
        val soundIndex = listOf(firstImageIndex, secondImageIndex).random()

        _state.update {
            it.copy(
                firstImageIndex = firstImageIndex,
                secondImageIndex = secondImageIndex,
                soundIndex = soundIndex,
                isLoading = false,
                isPlayingSound = true
            )
        }
    }

    fun onIntent(intent: GameIntent) {
        when (intent) {
            is GameIntent.OnImageClick -> onImageClick(intent.selectedImageIndex)
            is GameIntent.OnBackClick -> onBackClick()
            is GameIntent.OnTimeout -> onTimeout()
        }

    }

    private fun onTimeout() {
        _state.update {
            it.copy(
                isPlayingSound = false
            )
        }
        Navigator.sendEvent(NavEvent.NavigateTo(Screen.ResultsScreen.withArgs(_state.value.score.toString())))
    }

    private fun onBackClick() {
        _state.update {
            it.copy(
                score = 0,
                isPlayingSound = false
            )
        }
        Navigator.sendEvent(NavEvent.NavigateTo(Screen.StartScreen.route))
    }

    private fun onImageClick(selectedImageIndex: Int) {
        if (selectedImageIndex == _state.value.soundIndex) {
            _state.update {
                it.copy(
                    isPlayingSound = false,
                    showMessage = true,
                    isAnswerCorrect = true,
                    isClickable = false,
                    score = it.score + 1
                )
            }
        } else {
            _state.update {
                it.copy(
                    isPlayingSound = false,
                    showMessage = true,
                    isAnswerCorrect = false,
                    isClickable = false
                )
            }
        }
        nextGame()
    }

    private fun nextGame() {
        viewModelScope.launch {
            delay(2000L)
            drawGame()
            _state.update {
                it.copy(
                    isClickable = true,
                    showMessage = false
                )
            }
        }
    }
}