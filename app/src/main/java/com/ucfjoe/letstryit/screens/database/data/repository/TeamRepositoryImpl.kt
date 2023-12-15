package com.ucfjoe.letstryit.screens.database.data.repository

import com.ucfjoe.letstryit.screens.database.data.dao.TeamDao
import com.ucfjoe.letstryit.screens.database.data.entity.TeamEntity
import com.ucfjoe.letstryit.screens.database.data.entity.toTeam
import com.ucfjoe.letstryit.screens.database.data.entity.toTeamEntity
import com.ucfjoe.letstryit.screens.database.domain.model.Team
import com.ucfjoe.letstryit.screens.database.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamRepositoryImpl(
    private val teamDao: TeamDao
) : TeamRepository {
    override suspend fun upsertTeam(team: Team) {
        teamDao.upsertTeam(team.toTeamEntity())
    }

    override suspend fun deleteTeam(team: Team) {
        teamDao.deleteTeam(team.toTeamEntity())
    }

    override fun getTeams(): Flow<List<Team>> {
        return teamDao.getTeams().map { list -> list.map { it.toTeam() } }
    }

    override suspend fun createInitialData() {
        teamDao.upsertTeam(TeamEntity("Jaguars", "Jacksonville", "FL", "IDK 1", 0))
        teamDao.upsertTeam(TeamEntity("Dolphins", "Miami", "FL", "IDK 2", 0))
        teamDao.upsertTeam(TeamEntity("Buccaneers", "Tampa Bay", "FL", "IDK 3", 0))
    }
}