package com.henry.cleanarhpretest.data.mapper

import com.henry.cleanarhpretest.data.remote.dto.TodoDto
import com.henry.cleanarhpretest.domain.model.Todo

fun TodoDto.toTodo(): Todo {
    return Todo(
        userId = userId ?: 0,
        id = id ?: 0,
        title = title ?: "",
        completed = completed ?: false,
    )
}