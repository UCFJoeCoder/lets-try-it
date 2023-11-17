package com.ucfjoe.letstryit.retrofitexample

import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {

    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>

//    // Example Query parameters
//    @GET("/todos")
//    suspend fun getTodos(@Query("key") key:String): Response<List<Todo>>

//    @POST("/createTodo")
//    suspend fun createTodo(@Body todo:Todo): Response<CreateTodoResponse>
}