package com.ucfjoe.letstryit.screens.database.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucfjoe.letstryit.screens.database.presentation.DatabaseScreenEvents
import com.ucfjoe.letstryit.screens.database.presentation.DatabaseScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTeam(
    state: DatabaseScreenState,
    onEvent: (DatabaseScreenEvents) -> Unit
) {
    var isDropDownExpanded by remember { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ExposedDropdownMenuBox(
            expanded = isDropDownExpanded,
            onExpandedChange = { isDropDownExpanded = it }
        ) {
            OutlinedTextField(
                value = state.selectedTeam.name,
                onValueChange = { },
                readOnly = true,
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor(),
                label = { Text("Select Team") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropDownExpanded)
                }
            )
            ExposedDropdownMenu(
                expanded = isDropDownExpanded,
                onDismissRequest = { isDropDownExpanded = false }
            ) {
                state.teams.forEach { team ->
                    DropdownMenuItem(
                        text = { Text(team.name) },
                        onClick = {
                            onEvent(DatabaseScreenEvents.OnSelectedTeamChanged(team.name))
                            isDropDownExpanded = false
                        }
                    )
                }
            }
        }
        Button(onClick = { onEvent(DatabaseScreenEvents.OnCreateTeam) }, Modifier.padding(12.dp)) {
            Icon(Icons.Default.Check, "Add Team")
        }
    }
}