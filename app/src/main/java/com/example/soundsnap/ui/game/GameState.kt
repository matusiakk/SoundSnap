package com.example.soundsnap.ui.game

data class GameState(
    var isLoading: Boolean = true,
    var score: Int = 0,
    var category: Categories? = null,
    var firstImage: GameItem? = null,
    var secondImage: GameItem? = null,
    var sound: GameItem? = null,
    var isPlayingSound: Boolean = false,
    var showMessage: Boolean = false,
    var isAnswerCorrect: Boolean = false,
    var isClickable: Boolean = true
)