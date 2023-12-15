package com.ucfjoe.letstryit.screens.database.domain.model

data class Team(
    val name: String,
    val city: String,
    val state: String,
    val coach: String? = null,
    val id: Long = 0
)
