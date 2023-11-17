package com.ucfjoe.letstryit.marvel.apidata

data class EventList(
    val available: Int?,
    val collectionURI: String?,
    val items: List<EventSummary>?,
    val returned: Int?
)