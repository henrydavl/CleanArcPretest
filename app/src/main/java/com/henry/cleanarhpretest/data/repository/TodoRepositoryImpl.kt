package com.henry.cleanarhpretest.data.repository

import com.henry.cleanarhpretest.data.remote.TodoApi
import com.henry.cleanarhpretest.data.remote.dto.TodoDto
import com.henry.cleanarhpretest.domain.repository.TodoRepository
import com.henry.cleanarhpretest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
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