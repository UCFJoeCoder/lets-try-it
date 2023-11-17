package com.ucfjoe.letstryit.marvel.apidata

data class SeriesList(
    val available: Int?,
    val collectionURI: String?,
    val items: List<SeriesSummary>?,
    val returned: Int?
)