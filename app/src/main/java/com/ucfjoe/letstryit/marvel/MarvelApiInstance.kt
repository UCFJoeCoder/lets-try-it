package com.ucfjoe.letstryit.marvel

import android.content.Context
import coil.request.ImageRequest
import com.ucfjoe.letstryit.marvel.apidata.ErrorResponse
import com.ucfjoe.letstryit.marvel.apidata.Image
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object MarvelApiInstance {

    val api: MarvelApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }

    fun getMarvelImage(image: Image, context: Context): ImageRequest {
        return ImageRequest
            .Builder(context)
            .data("${image.path}.${image.extension}")
            .error(android.R.drawable.stat_sys_warning)
            .build()
    }

    fun getMarvelImageWithParameters(image: Image, context: Context): ImageRequest {
        return ImageRequest
            .Builder(context)
            .data("${image.path}.${image.extension}")
            .addHeader("apikey", Constants.API_KEY)
            .addHeader("hash", Constants.hash())
            .addHeader("ts", Constants.ts)
            .error(android.R.drawable.stat_sys_warning)
            .build()
    }

    fun parseError(response: Response<*>): ErrorResponse {
        val converter = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .responseBodyConverter<ErrorResponse>(
                ErrorResponse::class.java,
                arrayOfNulls<Annotation>(0)
            )

        val error: ErrorResponse

        try {
            error =
                converter.convert(response.errorBody()!!) ?: ErrorResponse(
                    "2",
                    "Error Body is null"
                )
        } catch (e: IOException) {
            return ErrorResponse("1", "Failed to parse error response")
        }

        return error
    }

}