package com.example.soundsnap.ui.game

import com.example.soundsnap.R

enum class GameItem(val image: Int, val sound: Int, val category: Categories) {
    DRUM(R.drawable.drum, R.raw.drum, Categories.INSTRUMENTS),
    ACOUSTIC_GUITAR(R.drawable.acoustic_guitar, R.raw.acoustic_guitar, Categories.INSTRUMENTS),
    PIANO(R.drawable.piano, R.raw.piano, Categories.INSTRUMENTS),
    FLUTE(R.drawable.flute, R.raw.flute, Categories.INSTRUMENTS),
    TAMBOURINE(R.drawable.tambourine, R.raw.tambourine, Categories.INSTRUMENTS),
    TRUMPET(R.drawable.trumpet, R.raw.trumpet, Categories.INSTRUMENTS),
    BAGPIPE(R.drawable.bagpipe, R.raw.bagpipe, Categories.INSTRUMENTS),
    CELLO(R.drawable.cello, R.raw.cello, Categories.INSTRUMENTS),
    DRUMS(R.drawable.drums, R.raw.drums, Categories.INSTRUMENTS),
    HARMONICA(R.drawable.harmonica, R.raw.harmonica, Categories.INSTRUMENTS),
    VIOLIN(R.drawable.violin, R.raw.violin, Categories.INSTRUMENTS),
    HARP(R.drawable.harp, R.raw.harp, Categories.INSTRUMENTS),
    HIPPO(R.drawable.hippo, R.raw.hippo, Categories.ANIMALS),
    DUCK(R.drawable.duck, R.raw.duck, Categories.ANIMALS),
    HORSE(R.drawable.horse, R.raw.horse, Categories.ANIMALS),
    ELEPHANT(R.drawable.elephant, R.raw.elephant, Categories.ANIMALS),
    COW(R.drawable.cow, R.raw.cow, Categories.ANIMALS),
    ROOSTER(R.drawable.rooster, R.raw.rooster, Categories.ANIMALS),
    CHICK(R.drawable.chick, R.raw.chick, Categories.ANIMALS),
    CAT(R.drawable.cat, R.raw.cat, Categories.ANIMALS),
    DOG(R.drawable.dog, R.raw.dog, Categories.ANIMALS),
    CHICKEN(R.drawable.chicken, R.raw.chicken, Categories.ANIMALS),
    AMBULANCE(R.drawable.ambulance, R.raw.ambulance, Categories.VEHICLES),
    BIKE(R.drawable.bike, R.raw.bike, Categories.VEHICLES),
    BOAT(R.drawable.boat, R.raw.boat, Categories.VEHICLES),
    HELICOPTER(R.drawable.helicopter, R.raw.helicopter, Categories.VEHICLES),
    MOTORCYCLE(R.drawable.motorcycle, R.raw.motorcycle, Categories.VEHICLES),
    POLICE(R.drawable.police, R.raw.police, Categories.VEHICLES),
    PLANE(R.drawable.plane, R.raw.plane, Categories.VEHICLES),
    TRAIN(R.drawable.train, R.raw.train, Categories.VEHICLES);

    companion object {
        fun getRandomItems(category: Categories): List<GameItem> {
            val firstItem = values().filter { it.category == category }.random()
            var secondItem = values().filter { it.category == category }.random()
            while (firstItem == secondItem)
                secondItem = values().filter { it.category == category }.random()
            val soundItem = listOf(firstItem, secondItem).random()
            return listOf(firstItem, secondItem, soundItem)
        }
    }
}
