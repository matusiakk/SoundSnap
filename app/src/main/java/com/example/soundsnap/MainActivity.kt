package com.example.soundsnap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.soundsnap.ui.nav.NavigationComponent
import com.example.soundsnap.ui.theme.SoundSnapTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoundSnapTheme {
                val navController = rememberNavController()
                NavigationComponent(navController = navController)
            }
        }
    }
}
