package com.ucfjoe.letstryit.screens.database.presentation

import com.ucfjoe.letstryit.screens.database.domain.model.Team

class TeamsConstants {
    companion object {
        fun getTeamByName(name: String) : Team {
            return listOfTeams.first { it.name == name }
        }

        val listOfTeams =
            listOf(
                Team("Steelers", "Pittsburgh", "PA"),
                Team("Raiders", "Las Vegas", "NV"),
                Team("Chiefs", "Kansas City", "MO"),
                Team("Cowboys", "Dallas", "TX"),
                Team("Panthers", "Charlotte", "NC"),
                Team("Saints", "New Orleans", "LA"),
                Team("Browns", "Cleveland", "OH"),
                Team("Broncos", "Denver", "CO"),
                Team("Jaguars", "Jacksonville", "FL"),
                Team("Commanders", "Landover", "MD"),
                Team("Lions", "Detroit", "MI"),
                Team("Patriots", "Foxborough", "MA"),
                Team("Dolphins", "Miami", "FL"),
                Team("Bills", "Orchard Park", "NY"),
                Team("Packers", "Green Bay", "WI"),
                Team("49ers", "Santa Clara", "CA"),
                Team("Eagles", "Philadelphia", "PA"),
                Team("Colts", "Indianapolis", "IN"),
                Team("Seahawks", "Seattle", "WA"),
                Team("Ravens", "Baltimore", "MD"),
                Team("Falcons", "Atlanta", "GA"),
                Team("Giants", "East Rutherford", "NJ"),
                Team("Jets", "East Rutherford", "NJ"),
                Team("Titans", "Nashville", "TN"),
                Team("Texans", "Houston", "TX"),
                Team("Bengals", "Cincinnati", "OH"),
                Team("Buccaneers", "Tampa", "FL"),
                Team("Rams", "Los Angeles", "CA"),
                Team("Chargers", "Los Angeles", "CA"),
                Team("Bears", "Chicago", "IL"),
                Team("Cardinals", "Phoenix", "AZ"),
                Team("Vikings", "Minneapolis", "MN")
            ).sortedBy { it.name }
    }
}