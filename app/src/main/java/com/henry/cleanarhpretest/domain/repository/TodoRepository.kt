package com.henry.cleanarhpretest.domain.repository

import com.henry.cleanarhpretest.domain.model.Todo
import com.henry.cleanarhpretest.utils.Resource

interface TodoRepository {
    suspend fun getTodoById(id: Int): Resource<Todo>
}