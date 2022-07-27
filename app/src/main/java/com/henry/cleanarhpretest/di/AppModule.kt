package com.henry.cleanarhpretest.di

import com.henry.cleanarhpretest.data.remote.TodoApi
import com.henry.cleanarhpretest.data.repository.TodoRepositoryImpl
import com.henry.cleanarhpretest.domain.repository.TodoRepository
import com.henry.cleanarhpretest.domain.use_case.GetTodoUseCase
import com.henry.cleanarhpretest.domain.use_case.TodoUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTodoApi(): TodoApi {
        return Retrofit.Builder()
            .baseUrl(TodoApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesTodoRepository(api: TodoApi): TodoRepository {
        return TodoRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesTodoUseCases(repository: TodoRepository): TodoUseCase {
        return TodoUseCase(
            getTodoUseCase = GetTodoUseCase(repository),
        )
    }
}