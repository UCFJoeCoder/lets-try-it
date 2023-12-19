package com.ucfjoe.letstryit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucfjoe.letstryit.screens.flowcombine.MutableStateFlowCombineScreen
import com.ucfjoe.letstryit.screens.retrofitexample.RetrofitScreen
import com.ucfjoe.letstryit.screens.themeexample.ThemeScreen

fun NavHostController.navigateAndPopBackStack(route: String) {
    popBackStack()
    navigate(route)
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    isDarkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    requestPhoneDarkModeEnabled: () -> Boolean
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ThemeExampleScreen.route
    ) {
        composable(route = Screen.ThemeExampleScreen.route) {
            ThemeScreen(
                isDarkTheme = isDarkTheme,
                onThemeUpdated = onThemeUpdated,
                requestPhoneDarkModeEnabled = requestPhoneDarkModeEnabled)
        }
        composable(route = Screen.RetrofitScreen.route) {
            RetrofitScreen()
        }
        composable(route = Screen.FlowCombineScreen.route) {
            MutableStateFlowCombineScreen()
        }
    }
}