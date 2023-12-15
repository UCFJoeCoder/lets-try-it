package com.ucfjoe.letstryit.screens.marvel.apidata

data class StoryList(
    val available: Int?,
    val collectionURI: String?,
    val items: List<StorySummary>?,
    val returned: Int?
)