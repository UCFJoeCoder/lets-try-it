package com.ucfjoe.letstryit.marvel.apidata

data class ComicList(
    val available: Int?,
    val collectionURI: String?,
    val items: List<ComicSummary>?,
    val returned: Int?
)