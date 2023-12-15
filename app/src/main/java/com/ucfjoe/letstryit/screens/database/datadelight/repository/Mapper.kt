package com.ucfjoe.letstryit.screens.database.datadelight.repository

import com.ucfjoe.letstryit.screens.database.domain.model.Team
import com.ucfjoe.letstryit.teamdb.Teams

fun Teams.toTeam() : Team {
    return Team(name, city, state, coach, id)
}

fun Team.toTeams() : Teams {
    return Teams(id, name, city, state, coach ?: "")
}
