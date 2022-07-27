package com.henry.cleanarhpretest.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henry.cleanarhpretest.domain.model.Todo
import com.henry.cleanarhpretest.domain.use_case.TodoUseCase
import com.henry.cleanarhpretest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
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
        val response = todoUseCase.getTodoUseCase(id)
        todoResponse.postValue(response)
    }
}