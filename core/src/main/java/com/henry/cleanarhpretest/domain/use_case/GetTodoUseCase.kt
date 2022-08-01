package com.henry.cleanarhpretest.domain.use_case

import com.henry.cleanarhpretest.data.mapper.toTodo
import com.henry.cleanarhpretest.domain.model.Todo
import com.henry.cleanarhpretest.domain.repository.TodoRepository
import com.henry.cleanarhpretest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetTodoUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(id: Int): Flow<Resource<Todo>> = flow {
        try {
            emit(Resource.Loading())
            val todo = repository.getTodoById(id)
            emit(Resource.Success(todo.data?.toTodo()))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't load Todo data"))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error(e.localizedMessage ?: "Bad Url"))
        }
    }
}