package com.example.soundsnap.ui.game

import com.example.soundsnap.R

data class GameImages(
    val instruments: List<Int> = listOf(
        R.drawable.drum,
        R.drawable.acoustic_guitar,
        R.drawable.piano,
        R.drawable.flute,
        R.drawable.tambourine,
        R.drawable.trumpet,
        R.drawable.bagpipe,
        R.drawable.cello,
        R.drawable.drums,
        R.drawable.harmonica,
        R.drawable.violin,
        R.drawable.harp
    ),
    val animals: List<Int> = listOf(
        R.drawable.hippo,
        R.drawable.duck,
        R.drawable.horse,
        R.drawable.elephant,
        R.drawable.cow,
        R.drawable.rooster,
        R.drawable.chick,
        R.drawable.cat,
        R.drawable.dog,
        R.drawable.chicken
    ),
    val vehicles: List<Int> = listOf(
        R.drawable.ambulance,
        R.drawable.bike,
        R.drawable.boat,
        R.drawable.helicopter,
        R.drawable.motorcycle,
        R.drawable.police,
        R.drawable.plane,
        R.drawable.train
    )
)
