package com.henry.cleanarhpretest.domain.repository

import com.henry.cleanarhpretest.data.dto.TodoDto
import com.henry.cleanarhpretest.utils.Resource

interface TodoRepository {
    suspend fun getTodoById(id: Int): Resource<TodoDto>
}