package com.ucfjoe.letstryit

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ucfjoe.letstryit.themeexample.ThemeTest
import com.ucfjoe.letstryit.ui.theme.LetsTryItTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            LetsTryItTheme(darkTheme = darkTheme, dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ThemeTest(isDarkTheme = darkTheme, onThemeUpdated = { darkTheme = !darkTheme }, requestPhoneDarkModeEnabled = this::isDarkModeOn)

                    //MutableStateFlowCombineScreen()
                    //RetrofitScreen()
                    //MarvelScreen()
                }
            }
        }
    }

    fun isDarkModeOn(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }
}

