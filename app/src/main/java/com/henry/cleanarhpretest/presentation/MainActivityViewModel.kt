package com.henry.cleanarhpretest.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henry.cleanarhpretest.domain.model.Todo
import com.henry.cleanarhpretest.use_case.TodoUseCase
import com.henry.cleanarhpretest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val todoUseCase: TodoUseCase
): ViewModel() {

    val todoResponse: MutableLiveData<Resource<Todo>> = MutableLiveData()

    init {
        getTodo(1)
    }

    fun getTodo(id: Int) = viewModelScope.launch {
        todoResponse.postValue(Resource.Loading())
        todoUseCase.getTodoUseCase(id)
            .catch { e ->
                todoResponse.postValue(Resource.Error(e.message ?: "An error occurred"))
            }.collect { result ->
                todoResponse.postValue((Resource.Success(result.data)))
            }
    }
}