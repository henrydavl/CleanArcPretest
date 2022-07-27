package com.henry.cleanarhpretest.data.repository

import com.henry.cleanarhpretest.data.mapper.toTodo
import com.henry.cleanarhpretest.data.remote.TodoApi
import com.henry.cleanarhpretest.domain.model.Todo
import com.henry.cleanarhpretest.domain.repository.TodoRepository
import com.henry.cleanarhpretest.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepositoryImpl @Inject constructor(
    private val api: TodoApi
): TodoRepository{
    override suspend fun getTodoById(id: Int): Resource<Todo> {
        return try {
            val response = api.getTodoById(id)
            Resource.Success(response.toTodo())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error("Couldn't load Todo data")
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error("Bad Url")
        }
    }
}