package com.example.soundsnap.ui.nav

sealed class Screen(val route: String) {
    object GameScreen : Screen("game")
    object StartScreen : Screen("start")
    object ResultsScreen : Screen("results")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

