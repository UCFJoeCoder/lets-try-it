package com.ucfjoe.letstryit.screens.database.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucfjoe.letstryit.screens.database.domain.model.Team
import com.ucfjoe.letstryit.screens.database.presentation.DatabaseScreenEvents
import com.ucfjoe.letstryit.screens.database.presentation.DatabaseType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeamInfo(
    team: Team,
    databaseType: DatabaseType,
    onEvent: (DatabaseScreenEvents) -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .combinedClickable(
                onClick = {},
                onLongClick = { onEvent(DatabaseScreenEvents.OnDeleteTeam(team, databaseType)) }
            )
    ) {
        Column(Modifier.padding(6.dp)) {
            Text(team.name)
            Text("${team.city}, ${team.state}")
        }
    }
}