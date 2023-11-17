package com.ucfjoe.letstryit.marvel

import com.ucfjoe.letstryit.marvel.apidata.CharacterDataWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Marvel API
 *
 * Your public key
 * 81865c13611bbc01ce742ec4ddc32a60
 *
 * Your private key
 * 58cbfc5eef463d5365f7ee50bc836b53671be3b8
 *
 */
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