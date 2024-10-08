package com.example.soundsnap.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.soundsnap.ui.game.GameScreen
import com.example.soundsnap.ui.results.ResultsScreen
import com.example.soundsnap.ui.start.StartScreen


@Composable
fun NavigationComponent(navController: NavHostController) {
    LaunchedEffect(Unit) {
        Navigator.getEventsFlow().collect { event ->
            when (event) {
                is NavEvent.NavigateBack -> navController.popBackStack()
                is NavEvent.NavigateTo -> navController.navigate(event.route)
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.StartScreen.route
    ) {
        composable(route = Screen.GameScreen.route + "/{category}") {
            GameScreen()
        }
        composable(route = Screen.StartScreen.route) {
            StartScreen()
        }
        composable(route = Screen.ResultsScreen.route + "/{score}"){
            ResultsScreen()
        }
    }

}


