package com.ucfjoe.letstryit.screens.database.presentation

import com.ucfjoe.letstryit.screens.database.domain.model.Team

data class DatabaseScreenState(
    val teamsRoom: List<Team> = emptyList(),
    val teamsSqlDelight: List<Team> = emptyList(),
    val activeDatabase: ActiveDatabase = ActiveDatabase.ROOM,
    val errorMessage: String = "",
    val selectedTeam: Team = Team("", "", "", ""),
    val teams: List<Team> = TeamsConstants.listOfTeams
)

enum class ActiveDatabase {
    ROOM, SQLDELIGHT, BOTH
}

enum class DatabaseType {
    ROOM, SQLDELIGHT
}
