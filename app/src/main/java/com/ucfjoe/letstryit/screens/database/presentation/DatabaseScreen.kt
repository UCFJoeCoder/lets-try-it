package com.ucfjoe.letstryit.screens.database.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucfjoe.letstryit.screens.database.data.TeamDatabase
import com.ucfjoe.letstryit.screens.database.data.repository.TeamRepositoryImpl
import com.ucfjoe.letstryit.screens.database.datadelight.TeamDatabaseDelight
import com.ucfjoe.letstryit.screens.database.domain.model.Team
import com.ucfjoe.letstryit.screens.database.presentation.composables.CreateTeam
import com.ucfjoe.letstryit.screens.database.presentation.composables.DatabaseRadioButtons
import com.ucfjoe.letstryit.screens.database.presentation.composables.TeamInfo

@Composable
fun DatabaseScreen() {

    // Should use dependency injection instead of placing all this here
    val context = LocalContext.current
    val db by lazy {
        TeamDatabase.getInstance(context)
    }
    val viewModel = viewModel<DatabaseViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val teamRepoRoom = TeamRepositoryImpl(db.teamDao)
                val teamRepoSqlDelight = TeamDatabaseDelight.getTeamRepositoryDelight(context)
                return DatabaseViewModel(teamRepoRoom, teamRepoSqlDelight) as T
            }
        }
    )
    // End of where dependency injection should be used.

    DatabaseScreen(viewModel.state.value, viewModel::onEvent)
}

@Composable
fun DatabaseScreen(
    state: DatabaseScreenState,
    onEvent: (DatabaseScreenEvents) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CreateTeam(state = state, onEvent = onEvent)

        DatabaseRadioButtons(state = state, onEvent = onEvent)

        Divider()

        Row(Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxWidth(.50f)) {
                Text("Room", Modifier.align(Alignment.CenterHorizontally))
                LazyColumn(Modifier.fillMaxSize()) {
                    items(state.teamsRoom) { team ->
                        TeamInfo(team, DatabaseType.ROOM, onEvent)
                    }
                }
            }
            Column(Modifier.fillMaxWidth()) {
                Text("SqlDelight", Modifier.align(Alignment.CenterHorizontally))
                LazyColumn(Modifier.fillMaxSize()) {
                    items(state.teamsSqlDelight) { team ->
                        TeamInfo(team, DatabaseType.SQLDELIGHT, onEvent)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDatabaseScreen() {
    DatabaseScreen(
        DatabaseScreenState(
            teamsRoom = listOf(
                Team("Broncos", "Denver", "CO", ""),
                Team("Chiefs", "Kansas City", "MO", ""),
                Team("Raiders", "Las Vegas", "NV", "")
            ),
            teamsSqlDelight = listOf(
                Team("Dolphins", "Miami", "FL", "Somebody"),
                Team("Jaguars", "Jacksonville", "FL", "Someone")
            )
        ), {})
}


