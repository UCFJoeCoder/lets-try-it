package com.ucfjoe.letstryit.screens.database.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucfjoe.letstryit.screens.database.domain.repository.TeamRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DatabaseViewModel(
    private val teamRepositoryRoom: TeamRepository,
    private val teamRepositorySqlDelight: TeamRepository
) : ViewModel() {

    private val _state = mutableStateOf(DatabaseScreenState())
    val state: State<DatabaseScreenState> = _state

    init {
        viewModelScope.launch {
            teamRepositoryRoom.getTeams().onEach { teams ->
                _state.value = state.value.copy(teamsRoom = teams)
            }.launchIn(viewModelScope)

            teamRepositorySqlDelight.getTeams().onEach { teams ->
                _state.value = state.value.copy(teamsSqlDelight = teams)
            }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: DatabaseScreenEvents) {
        when (event) {
            is DatabaseScreenEvents.OnActiveDatabaseChanged -> {
                _state.value = state.value.copy(activeDatabase = event.activeDatabase)
            }

            is DatabaseScreenEvents.OnDeleteTeam -> {
                if (event.databaseType == DatabaseType.ROOM) {
                    viewModelScope.launch {
                        teamRepositoryRoom.deleteTeam(event.team)
                    }
                } else {
                    viewModelScope.launch {
                        teamRepositorySqlDelight.deleteTeam(event.team)
                    }
                }
            }

            is DatabaseScreenEvents.OnSelectedTeamChanged -> {
                _state.value = state.value.copy(selectedTeam = TeamsConstants.getTeamByName(event.teamName))
            }

            DatabaseScreenEvents.OnSeedDatabases -> {
                viewModelScope.launch {
                    teamRepositoryRoom.createInitialData()
                    teamRepositorySqlDelight.createInitialData()
                }
            }

            DatabaseScreenEvents.OnCreateTeam -> {


                if (state.value.selectedTeam.name.isBlank())
                {
                    return
                }

                val newTeam = state.value.selectedTeam

                val activeDatabase = state.value.activeDatabase
                if (activeDatabase == ActiveDatabase.ROOM || activeDatabase == ActiveDatabase.BOTH) {
                    viewModelScope.launch {
                        teamRepositoryRoom.upsertTeam(newTeam)
                    }
                }

                if (activeDatabase == ActiveDatabase.SQLDELIGHT || activeDatabase == ActiveDatabase.BOTH) {
                    viewModelScope.launch {
                        teamRepositorySqlDelight.upsertTeam(newTeam)
                    }
                }
            }
        }
    }
}

