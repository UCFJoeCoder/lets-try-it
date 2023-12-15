package com.ucfjoe.letstryit.screens.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ucfjoe.letstryit.screens.database.domain.model.Team

@Entity(tableName = "teams")
data class TeamEntity(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "state")
    val state: String,

    @ColumnInfo(name = "coach")
    val coach: String? = null,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0
)

fun TeamEntity.toTeam(): Team {
    return Team(name, city, state, coach, id)
}

fun Team.toTeamEntity(): TeamEntity {
    return TeamEntity(name, city, state, coach, id)
}
