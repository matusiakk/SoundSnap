package com.example.soundsnap.ui.game

import com.example.soundsnap.R

data class GameSounds(
    val instruments: List<Int> = listOf(
        R.raw.drum,
        R.raw.acoustic_guitar,
        R.raw.piano,
        R.raw.flute,
        R.raw.tambourine,
        R.raw.trumpet,
        R.raw.bagpipe,
        R.raw.cello,
        R.raw.drums,
        R.raw.harmonica,
        R.raw.violin,
        R.raw.harp
    ),
    val animals: List<Int> = listOf(
        R.raw.hippo,
        R.raw.duck,
        R.raw.horse,
        R.raw.elephant,
        R.raw.cow,
        R.raw.rooster,
        R.raw.chick,
        R.raw.cat,
        R.raw.dog,
        R.raw.chicken
    ),
    val vehicles: List<Int> = listOf(
        R.raw.ambulance,
        R.raw.bike,
        R.raw.boat,
        R.raw.helicopter,
        R.raw.motorcycle,
        R.raw.police,
        R.raw.plane,
        R.raw.train
    )
)
