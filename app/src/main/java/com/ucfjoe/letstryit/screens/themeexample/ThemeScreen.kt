package com.ucfjoe.letstryit.screens.themeexample

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ucfjoe.letstryit.R


@Composable
fun ThemeScreen(
    isDarkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    requestPhoneDarkModeEnabled: () -> Boolean
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(top = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ToggleThemeButton(isDarkTheme = isDarkTheme, onThemeUpdated = onThemeUpdated)

            ExpandableList(
                sections = listOf(
                    SectionData(headerText = "Text Fields") { TextFields() },
                    SectionData(headerText = "Radios & Checkboxes") {
                        RadiosAndCheckBoxes(requestPhoneDarkModeEnabled)
                    },
                    SectionData(headerText = "Card Styles") { CardStyles() },
                    SectionData(headerText = "Text Styles") { TextStyles() }
                ))
        }
    }
}

@Composable
fun ToggleThemeButton(
    isDarkTheme: Boolean,
    onThemeUpdated: () -> Unit,
) {
    Button(
        onClick = { onThemeUpdated() }
    ) {
        Text("Toggle Theme")
        Spacer(modifier = Modifier.width(6.dp))
        if (isDarkTheme) {
            Icon(
                painter = painterResource(R.drawable.outline_light_mode_24),
                contentDescription = "Light Theme"
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.outline_dark_mode_24),
                contentDescription = "Dark Theme"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFields() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        var isError by remember { mutableStateOf(false) }
        var text by remember { mutableStateOf("") }
        var outlinedText by remember { mutableStateOf("") }
        OutlinedButton(onClick = { isError = !isError }) {
            Text("I toggle an Error")
        }
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Text field label") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "person") },
            supportingText = { if (isError) Text("We have an error") },
            isError = isError
        )
        OutlinedTextField(
            value = outlinedText,
            onValueChange = { outlinedText = it },
            trailingIcon = { Icon(Icons.Default.Send, contentDescription = "Send") }
        )
    }
}

@Composable
fun RadiosAndCheckBoxes(requestPhoneDarkModeEnabled: () -> Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        val context = LocalContext.current
        val radio1 = "Radio1"
        val radio2 = "Radio2"
        val selectedOption = remember { mutableStateOf(radio1) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RadioButton(
                selected = selectedOption.value == radio1,
                onClick = { selectedOption.value = radio1 })
            Text(radio1, modifier = Modifier.clickable { selectedOption.value = radio1 })
            RadioButton(
                selected = selectedOption.value == radio2,
                onClick = { selectedOption.value = radio2 })
            Text(radio2, modifier = Modifier.clickable { selectedOption.value = radio2 })
            Button(onClick = {
                val phoneDarkMode = requestPhoneDarkModeEnabled()
                Toast.makeText(
                    context,
                    "Phone's Setting - IsDarkMode:$phoneDarkMode",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                if (selectedOption.value == radio1) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                } else {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically)
        {
            var isChecked by remember { mutableStateOf(false) }
            Switch(checked = isChecked, onCheckedChange = { isChecked = !isChecked })

            val isChecked1 = remember { mutableStateOf(false) }
            val isChecked2 = remember { mutableStateOf(false) }
            Checkbox(checked = isChecked1.value, onCheckedChange = { isChecked1.value = it })
            Text("CB1", modifier = Modifier.clickable { isChecked1.value = !isChecked1.value })
            Checkbox(checked = isChecked2.value, onCheckedChange = { isChecked2.value = it })
            Text("CB2", modifier = Modifier.clickable { isChecked2.value = !isChecked2.value })
        }
    }
}

@Composable
fun CardStyles() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        ExampleCard(
            title = "Default Card",
            contentText = "Text OnDefaultContainer"
        )

        ExampleCard(
            title = "Primary Card",
            contentText = "Text OnPrimaryContainer",
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            onContainerColor = MaterialTheme.colorScheme.onPrimaryContainer
        )

        ExampleCard(
            title = "Secondary Card",
            contentText = "Text OnSecondaryContainer",
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            onContainerColor = MaterialTheme.colorScheme.onSecondaryContainer
        )

        ExampleCard(
            title = "TertiaryContainer Card",
            contentText = "Text OnTertiaryContainer",
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            onContainerColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }
}

@Composable
fun ExampleCard(
    title: String,
    contentText: String,
    containerColor: Color? = null,
    onContainerColor: Color? = null
) {
    val colors = if (containerColor != null && onContainerColor != null) {
        CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = onContainerColor
        )
    } else {
        CardDefaults.cardColors()
    }

    Card(
        modifier = Modifier.fillMaxWidth(.9f),
        colors = colors
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Text(contentText)
        }
    }
}

@Composable
fun TextStyles() {
    val radioStart = "Left"
    val radioCenter = "Center"
    val radioEnd = "Right"
    val selectedOption = remember { mutableStateOf(radioCenter) }
    val textHorizontalAlignment = remember { mutableStateOf(Alignment.CenterHorizontally) }

    fun moveTextStart() {
        selectedOption.value = radioStart
        textHorizontalAlignment.value = Alignment.Start
    }

    fun moveTextCenter() {
        selectedOption.value = radioCenter
        textHorizontalAlignment.value = Alignment.CenterHorizontally
    }

    fun moveTextEnd() {
        selectedOption.value = radioEnd
        textHorizontalAlignment.value = Alignment.End
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = textHorizontalAlignment.value,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedOption.value == radioStart,
                onClick = { moveTextStart() })
            Text(radioStart, modifier = Modifier.clickable { moveTextStart() })
            RadioButton(
                selected = selectedOption.value == radioCenter,
                onClick = { moveTextCenter() })
            Text(radioCenter, modifier = Modifier.clickable { moveTextCenter() })
            RadioButton(
                selected = selectedOption.value == radioEnd,
                onClick = { moveTextEnd() })
            Text(radioEnd, modifier = Modifier.clickable { moveTextEnd() })
        }

        Text("DisplayLarge", style = MaterialTheme.typography.displayLarge)
        Text("DisplayMedium", style = MaterialTheme.typography.displayMedium)
        Text("DisplaySmall", style = MaterialTheme.typography.displaySmall)
        Divider()
        Text("HeadlineLarge", style = MaterialTheme.typography.headlineLarge)
        Text("HeadlineMedium", style = MaterialTheme.typography.headlineMedium)
        Text("HeadlineSmall", style = MaterialTheme.typography.headlineSmall)
        Divider()
        Text("TitleLarge", style = MaterialTheme.typography.titleLarge)
        Text("TitleMedium", style = MaterialTheme.typography.titleMedium)
        Text("TitleSmall", style = MaterialTheme.typography.titleSmall)
        Divider()
        Text("BodyLarge", style = MaterialTheme.typography.bodyLarge)
        Text("BodyMedium", style = MaterialTheme.typography.bodyMedium)
        Text("BodySmall", style = MaterialTheme.typography.bodySmall)
        Divider()
        Text("LabelLarge", style = MaterialTheme.typography.labelLarge)
        Text("LabelMedium", style = MaterialTheme.typography.labelMedium)
        Text("LabelSmall", style = MaterialTheme.typography.labelSmall)
    }


}