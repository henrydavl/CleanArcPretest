package com.henry.cleanarhpretest.domain.use_case

import com.henry.cleanarhpretest.domain.model.Todo
import com.henry.cleanarhpretest.domain.repository.TodoRepository
import com.henry.cleanarhpretest.utils.Resource

class GetTodoUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(id: Int): Resource<Todo> {
        return repository.getTodoById(id)
    }
}