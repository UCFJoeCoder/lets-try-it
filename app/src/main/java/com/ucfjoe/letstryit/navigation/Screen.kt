package com.ucfjoe.letstryit.navigation

sealed class Screen(val route: String) {
    data object MarvelScreen : Screen("marvel")
    data object ThemeExampleScreen : Screen("theme_example")
    data object FlowCombineScreen : Screen("flow_combine")
    data object RetrofitScreen : Screen("retrofit")
    data object DatabaseScreen : Screen("database")
}
