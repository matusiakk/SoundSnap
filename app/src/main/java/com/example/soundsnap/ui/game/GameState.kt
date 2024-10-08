package com.example.soundsnap.ui.game

data class GameState(
    var isLoading: Boolean = true,
    var score: Int = 0,
    var category: Categories = Categories.Instruments,
    var firstImage: Int = 0,
    var secondImage: Int = 0,
    var sound: Int = 0,
    var firstImageIndex: Int = 0,
    var secondImageIndex: Int = 0,
    var soundIndex: Int = 0,
    var isPlayingSound: Boolean = false,
    var showMessage: Boolean = false,
    var isAnswerCorrect: Boolean = false,
    var isClickable: Boolean = true
)