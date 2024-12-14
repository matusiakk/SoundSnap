package com.example.soundsnap

import com.example.soundsnap.ui.game.GameState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object GameModule {

    @Provides
    fun provideGameState(): GameState {
        return GameState()
    }
}
