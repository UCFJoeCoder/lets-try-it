package com.ucfjoe.letstryit.screens.database.datadelight.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ucfjoe.letstryit.TeamDatabase
import com.ucfjoe.letstryit.screens.database.domain.model.Team
import com.ucfjoe.letstryit.screens.database.domain.repository.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamRepositoryDelightImpl(
    db: TeamDatabase
) : TeamRepository {

    private val queries = db.teamEntityQueries

    override suspend fun upsertTeam(team: Team) {
        val id = if (team.id == 0L) null else team.id
        queries.upsertTeam(id, team.name, team.city, team.state, team.coach ?: "")
    }

    override suspend fun deleteTeam(team: Team) {
        queries.deleteTeamById(team.id)
    }

    override fun getTeams(): Flow<List<Team>> {
        return queries.getAllTeams().asFlow().mapToList(Dispatchers.IO)
            .map { list -> list.map { it.toTeam() } }
    }

    override suspend fun createInitialData() {
        queries.upsertTeam(null, "Bears", "Chicago", "IL", "Ditka")
        queries.upsertTeam(null, "Broncos", "Denver", "CO", "Shanahan")
        queries.upsertTeam(null, "Sea Hawks", "Seattle", "WA", "Carroll")
    }
}