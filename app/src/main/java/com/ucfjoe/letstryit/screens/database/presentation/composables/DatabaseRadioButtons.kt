package com.ucfjoe.letstryit.screens.database.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucfjoe.letstryit.screens.database.presentation.ActiveDatabase
import com.ucfjoe.letstryit.screens.database.presentation.DatabaseScreenEvents
import com.ucfjoe.letstryit.screens.database.presentation.DatabaseScreenState

@Composable
fun DatabaseRadioButtons(
    state: DatabaseScreenState,
    onEvent: (DatabaseScreenEvents) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Row(
            Modifier
                .fillMaxWidth(.33f)
                .clickable { onEvent(DatabaseScreenEvents.OnActiveDatabaseChanged(ActiveDatabase.ROOM)) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = state.activeDatabase == ActiveDatabase.ROOM,
                onClick = { }
            )
            Text("Room", Modifier.padding(end = 6.dp))
        }
        Row(
            Modifier
                .fillMaxWidth(.5f)
                .clickable { onEvent(DatabaseScreenEvents.OnActiveDatabaseChanged(ActiveDatabase.BOTH)) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = state.activeDatabase == ActiveDatabase.BOTH,
                onClick = { }
            )
            Text("Both", Modifier.padding(end = 6.dp))
        }
        Row(
            Modifier
                .fillMaxWidth()
                .clickable { onEvent(DatabaseScreenEvents.OnActiveDatabaseChanged(ActiveDatabase.SQLDELIGHT)) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = state.activeDatabase == ActiveDatabase.SQLDELIGHT,
                onClick = { }
            )
            Text("SqlDelight")
        }
    }
}