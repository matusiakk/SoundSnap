package com.example.soundsnap.ui.game

import com.example.soundsnap.R

data class GameState(
    var isLoading: Boolean = true,
    var score: Int = 0,
    var sounds: List<Int> = listOf(
        R.raw.drum,
        R.raw.acoustic_guitar,
        R.raw.piano,
        R.raw.recorder,
        R.raw.tambourine,
        R.raw.trumpet,
        R.raw.bagpipe,
        R.raw.cello,
        R.raw.drums,
        R.raw.harmonica,
        R.raw.violin,
        R.raw.harp
    ),
    var images: List<Int> = listOf(
        R.drawable.drum,
        R.drawable.acoustic_guitar,
        R.drawable.piano,
        R.drawable.recorder,
        R.drawable.tambourine,
        R.drawable.trumpet,
        R.drawable.bagpipe,
        R.drawable.cello,
        R.drawable.drums,
        R.drawable.harmonica,
        R.drawable.violin,
        R.drawable.harp
    ),
    var firstImageIndex: Int = 0,
    var secondImageIndex: Int = 0,
    var soundIndex: Int = 0,
    var isPlayingSound: Boolean = false,
    var showMessage: Boolean = false,
    var isAnswerCorrect: Boolean = false,
    var isClickable: Boolean = true
)