package com.henry.cleanarhpretest.domain.repository

import com.henry.cleanarhpretest.data.remote.dto.TodoDto
import com.henry.cleanarhpretest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getTodoById(id: Int): Resource<TodoDto>
}