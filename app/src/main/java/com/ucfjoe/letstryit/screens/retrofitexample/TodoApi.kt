package com.ucfjoe.letstryit.screens.retrofitexample

import retrofit2.Response
import retrofit2.http.GET

fun interface TodoApi {

    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>

}