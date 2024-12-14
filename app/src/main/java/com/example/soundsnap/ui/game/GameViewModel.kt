package com.example.soundsnap.ui.game

import androidx.lifecycle.SavedStateHandle
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

@HiltViewModel
class GameViewModel @Inject constructor(
    initialState: GameState,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<GameState> = _state

    init {
        startGame(savedStateHandle.get<String>("category")!!)
    }

    private fun startGame(category: String) {
        val category: Categories = category.let {
            Categories.valueOf(it)
        }
        _state.update {
            it.copy(
                isLoading = true,
                showMessage = false,
                isClickable = true,
                category = category
            )
        }
        drawGame(category)
    }


    private fun drawGame(category: Categories) {
        val gameItems = GameItem.getRandomItems(category)

        _state.update {
            it.copy(
                firstImage = gameItems.first,
                secondImage = gameItems.second,
                sound = gameItems.third,
                isLoading = false,
                isPlayingSound = true
            )
        }
    }

    fun onIntent(intent: GameIntent) {
        when (intent) {
            is GameIntent.OnImageClick -> onImageClick(intent.selectedImage)
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
        Navigator.sendEvent(NavEvent.NavigateBack)
    }

    private fun onImageClick(selectedImage: Int) {
        if (selectedImage == _state.value.sound) {
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
            delay(1000L)
            drawGame(state.value.category!!)
            _state.update {
                it.copy(
                    isClickable = true,
                    showMessage = false
                )
            }
        }
    }
}