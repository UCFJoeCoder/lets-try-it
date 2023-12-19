package com.ucfjoe.letstryit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ucfjoe.letstryit.navigation.AppBar
import com.ucfjoe.letstryit.navigation.Navigation
import com.ucfjoe.letstryit.navigation.NavigationDrawerSheet
import com.ucfjoe.letstryit.navigation.NavigationItem
import com.ucfjoe.letstryit.navigation.Screen
import com.ucfjoe.letstryit.navigation.navigateAndPopBackStack
import com.ucfjoe.letstryit.ui.theme.LetsTryItTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCompose(
    isDarkMode: () -> Boolean
) {
    val navController: NavHostController = rememberNavController()
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var darkTheme by remember { mutableStateOf(false) }
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(value = 0) }

    // Turn off dynamic colors and use the dark and light themes defined in the ui.theme
    LetsTryItTheme(darkTheme = darkTheme, dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val scope = rememberCoroutineScope()

            Scaffold(
                topBar = {
                    AppBar(onAppBarActionClick = {
                        scope.launch {
                            if (drawerState.isClosed) {
                                drawerState.open()
                            } else {
                                drawerState.close()
                            }
                        }
                    })
                }
            ) { padding ->
                ModalNavigationDrawer(
                    modifier = Modifier.padding(padding),
                    drawerState = drawerState,
                    drawerContent = {
                        NavigationDrawerSheet(
                            selectedItemIndex = selectedItemIndex,
                            onItemSelected = { index, item ->
                                selectedItemIndex = index
                                scope.launch { drawerState.close() }
                                navController.navigateAndPopBackStack(item.screen.route)
                            }
                        )
                    }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Navigation(
                            navController = navController,
                            isDarkTheme = darkTheme,
                            onThemeUpdated = { darkTheme = !darkTheme },
                            requestPhoneDarkModeEnabled = isDarkMode
                        )
                    }
                }
            }
        }
    }
}

object DrawerParams {
    val drawerNavigationItems = listOf(
        NavigationItem(
            "Theme",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            screen = Screen.ThemeExampleScreen
        ),
        NavigationItem(
            "Retrofit",
            selectedIcon = Icons.Filled.AddCircle,
            unselectedIcon = Icons.Outlined.AddCircle,
            badgeCount = 23,
            screen = Screen.RetrofitScreen
        ),
        NavigationItem(
            "Flow Combine",
            selectedIcon = Icons.Filled.Face,
            unselectedIcon = Icons.Outlined.Face,
            screen = Screen.FlowCombineScreen
        )
    )
}