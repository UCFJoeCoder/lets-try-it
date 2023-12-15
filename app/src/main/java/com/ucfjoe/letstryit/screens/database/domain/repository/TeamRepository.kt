package com.ucfjoe.letstryit.screens.database.domain.repository

import com.ucfjoe.letstryit.screens.database.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface TeamRepository {
    suspend fun upsertTeam(team: Team)
    suspend fun deleteTeam(team: Team)
    fun getTeams(): Flow<List<Team>>
    suspend fun createInitialData()
}