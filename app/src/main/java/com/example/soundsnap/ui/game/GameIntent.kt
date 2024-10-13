package com.example.soundsnap.ui.game

sealed class GameIntent {
    data class OnImageClick(var selectedImage: GameItem?) : GameIntent()
    object OnBackClick : GameIntent()
    object OnTimeout : GameIntent()
}