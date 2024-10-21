package com.example.soundsnap.ui.start

import com.example.soundsnap.ui.game.Categories

sealed class StartIntent {
    class OnGameClick(var category: Categories) : StartIntent()
    object OnOptionsClick : StartIntent()
    object OnOptionsDismiss : StartIntent()
    object OnAboutClick : StartIntent()
}