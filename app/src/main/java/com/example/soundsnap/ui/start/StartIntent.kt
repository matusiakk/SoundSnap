package com.example.soundsnap.ui.start

sealed class StartIntent {
    object OnGameClick : StartIntent()
}