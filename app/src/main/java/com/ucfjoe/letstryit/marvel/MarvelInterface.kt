package com.ucfjoe.letstryit.marvel

import com.ucfjoe.letstryit.marvel.apidata.CharacterDataWrapper
import retrofit2.HttpException
import java.io.IOException

class MarvelResponse<T>(
    val result: T?,
    val error: String?
) {
    fun isSuccess(): Boolean {
        return result != null
    }
}


suspend fun getCharactersStartsWith(nameStartsWith: String): MarvelResponse<CharacterDataWrapper?> {

    var errorMessage: String = ""
    var characterDataWrapper: CharacterDataWrapper? = null

    val response = try {
        MarvelApiInstance.api.getCharactersStartsWith(
            nameStartsWith,
            Constants.API_KEY,
            Constants.hash(),
            Constants.ts
        )
    } catch (e: IOException) {
        errorMessage = "IOException, you might now have an internet connection"
        null
    } catch (e: HttpException) {
        errorMessage = "HttpException, unexpected response. website may be down"
        null
    }
    if (response != null) {
        if (response.isSuccessful && (response.body() != null)) {
            characterDataWrapper = response.body()!!
        } else {
            try {
                errorMessage = MarvelApiInstance.parseError(response).message
            } catch (e: Exception) {
                errorMessage = e.message ?: "Error performing json cast of real error"
            }
        }
    }

    return MarvelResponse(characterDataWrapper, errorMessage)
}