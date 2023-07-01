package com.example.anothertodo

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("list")
    suspend fun fetchTasks(): List<TodoItem>

    @PATCH("list")
    suspend fun patchTasks(@Body tasks: List<TodoItem>)

    @GET("list/{taskId}")
    suspend fun fetchTask(@Path("taskId") taskId: String): TodoItem

    @POST("list")
    suspend fun addTask(@Body task: TodoItem)

    @DELETE("list/{taskId}")
    suspend fun deleteTask(@Path("taskId") taskId: String)

    @PUT("list/{taskId}")
    suspend fun updateTask(@Path("taskId") taskId: String, @Body task: TodoItem)
}
