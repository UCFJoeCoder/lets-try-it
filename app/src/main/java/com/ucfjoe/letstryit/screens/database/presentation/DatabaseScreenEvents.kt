package com.ucfjoe.letstryit.screens.database.presentation

import com.ucfjoe.letstryit.screens.database.domain.model.Team

sealed class DatabaseScreenEvents {
    data object OnSeedDatabases : DatabaseScreenEvents()
    data class OnActiveDatabaseChanged(val activeDatabase: ActiveDatabase) :DatabaseScreenEvents()

    data class OnSelectedTeamChanged(val teamName: String) : DatabaseScreenEvents()
    data object OnCreateTeam : DatabaseScreenEvents()
    data class OnDeleteTeam(val team: Team, val databaseType: DatabaseType) : DatabaseScreenEvents()
}
