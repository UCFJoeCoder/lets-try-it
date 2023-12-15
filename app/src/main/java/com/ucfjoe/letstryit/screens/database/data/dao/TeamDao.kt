package com.ucfjoe.letstryit.screens.database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ucfjoe.letstryit.screens.database.data.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Upsert
    suspend fun upsertTeam(team: TeamEntity): Long

    @Delete
    suspend fun deleteTeam(team: TeamEntity)

    @Query("SELECT * FROM teams ORDER BY UPPER(name)")
    fun getTeams(): Flow<List<TeamEntity>>

}