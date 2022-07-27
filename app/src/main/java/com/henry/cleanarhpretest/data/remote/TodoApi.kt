package com.henry.cleanarhpretest.data.remote

import com.henry.cleanarhpretest.data.remote.dto.TodoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoApi {
    @GET("todos/{id}")
    suspend fun getTodoById(
        @Path("id") todoId: Int
    ): TodoDto

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}