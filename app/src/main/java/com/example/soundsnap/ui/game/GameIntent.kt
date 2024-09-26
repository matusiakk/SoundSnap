package com.example.soundsnap.ui.game

sealed class GameIntent {
    data class OnImageClick(var selectedImageIndex: Int) : GameIntent()
    object OnBackClick : GameIntent()
    object OnTimeout : GameIntent()
}