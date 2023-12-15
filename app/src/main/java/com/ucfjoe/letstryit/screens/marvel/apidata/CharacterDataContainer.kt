package com.ucfjoe.letstryit.screens.marvel.apidata

data class CharacterDataContainer(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val results: List<Character>?,
    val total: Int?
)