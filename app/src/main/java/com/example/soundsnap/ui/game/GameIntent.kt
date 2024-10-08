package com.example.soundsnap.ui.game

sealed class GameIntent {
    data class OnImageClick(var selectedImage: Int) : GameIntent()
    object OnBackClick : GameIntent()
    object OnTimeout : GameIntent()
}