package com.ucfjoe.letstryit.screens.marvel

import com.ucfjoe.letstryit.screens.marvel.apidata.CharacterDataWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getCharactersStartsWith(
        @Query("nameStartsWith") startsWith: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Response<CharacterDataWrapper>

    @GET("/v1/public/character")
    suspend fun getCharacterByName(
        @Query("name") name: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Response<CharacterDataWrapper>

}