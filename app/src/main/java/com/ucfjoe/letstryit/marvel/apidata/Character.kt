package com.ucfjoe.letstryit.marvel.apidata

data class Character(
    val comics: ComicList?,
    val description: String?,
    val events: EventList?,
    val id: String?,
    val modified: String?,
    val name: String?,
    val resourceURI: String?,
    val series: SeriesList?,
    val stories: StoryList?,
    val thumbnail: Image?,
    val urls: List<Url>?
)