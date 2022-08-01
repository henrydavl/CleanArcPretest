package com.henry.cleanarhpretest.data.repository

import com.henry.cleanarhpretest.data.remote.TodoApi
import com.henry.cleanarhpretest.data.dto.TodoDto
import com.henry.cleanarhpretest.domain.repository.TodoRepository
import com.henry.cleanarhpretest.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepositoryImpl @Inject constructor(
    private val api: TodoApi
) : TodoRepository {
    override suspend fun getTodoById(id: Int): Resource<TodoDto> {
        return Resource.Success(api.getTodoById(id))
    }
}