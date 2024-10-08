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
import kotlin.random.Random

@HiltViewModel
class GameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(GameState())
    val state: StateFlow<GameState> = _state

    init {
        startGame(savedStateHandle.get<String>("category")!!)
    }

    private fun startGame(category: String) {
        val category: Categories = category!!.let {
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

    private fun getRandomImageIndex(category: Categories, images: GameImages): Int {
        return when (category){
            Categories.Instruments -> Random.nextInt(images.instruments.size)
            Categories.Animals -> Random.nextInt(images.animals.size)
            Categories.Vehicles -> Random.nextInt(images.vehicles.size)
        }
    }
    private fun drawGame(category: Categories) {
        val images = GameImages()
        val sounds = GameSounds()
        val firstImageIndex = getRandomImageIndex(category, images)
        var secondImageIndex = getRandomImageIndex(category, images)
        while (firstImageIndex == secondImageIndex)
            secondImageIndex = getRandomImageIndex(category, images)
        val soundIndex = listOf(firstImageIndex, secondImageIndex).random()

        _state.update {
            it.copy(
                firstImage = when (category){
                    Categories.Instruments -> images.instruments[firstImageIndex]
                    Categories.Animals -> images.animals[firstImageIndex]
                    Categories.Vehicles -> images.vehicles[firstImageIndex]
                },
                secondImage = when (category){
                    Categories.Instruments -> images.instruments[secondImageIndex]
                    Categories.Animals -> images.animals[secondImageIndex]
                    Categories.Vehicles -> images.vehicles[secondImageIndex]
                },
                sound = when (category){
                    Categories.Instruments -> sounds.instruments[soundIndex]
                    Categories.Animals -> sounds.animals[soundIndex]
                    Categories.Vehicles -> sounds.vehicles[soundIndex]
                },
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
        _state.update {
            it.copy(
                score = 0,
                isPlayingSound = false
            )
        }
        Navigator.sendEvent(NavEvent.NavigateTo(Screen.StartScreen.route))
    }

    private fun onImageClick(selectedImage: Int) {
        if (selectedImage == _state.value.soundIndex) {
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
            drawGame(state.value.category)
            _state.update {
                it.copy(
                    isClickable = true,
                    showMessage = false
                )
            }
        }
    }
}