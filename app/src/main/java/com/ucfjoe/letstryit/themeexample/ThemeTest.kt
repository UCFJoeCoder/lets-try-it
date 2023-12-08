package com.ucfjoe.letstryit.themeexample

import android.icu.text.ListFormatter.Width
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
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Snackbar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ucfjoe.letstryit.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeTest(
    isDarkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    requestPhoneDarkModeEnabled: () -> Boolean
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            var isError by remember { mutableStateOf(false) }
            var text by remember { mutableStateOf("") }
            var outlinedText by remember { mutableStateOf("") }
            Text("Simple line of text")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
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
                OutlinedButton(onClick = { isError = !isError }) {
                    Text("I toggle an Error")
                }
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
            Divider(Modifier.padding(horizontal = 32.dp, vertical = 6.dp))

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
                    Toast.makeText(context, "Phone's Setting - IsDarkMode:$phoneDarkMode", Toast.LENGTH_SHORT).show()
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

            Card(
                modifier = Modifier
                    .fillMaxWidth(.9f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("TertiaryContainer Card", style = MaterialTheme.typography.titleLarge)
                    Text("Text OnTertiaryContainer")
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth(.9f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Secondary Card", style = MaterialTheme.typography.titleLarge)
                    Text("Text OnSecondaryContainer")
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth(.9f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Primary Card", style = MaterialTheme.typography.titleLarge)
                    Text("Text OnPrimaryContainer")
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth(.9f),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Default Card", style = MaterialTheme.typography.titleLarge)
                    Text("Text OnDefaultContainer")
                }
            }
        }
    }
}