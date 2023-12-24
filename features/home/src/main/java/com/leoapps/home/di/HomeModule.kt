package com.leoapps.home.di

import com.leoapps.home.domain.QuizRepository
import com.leoapps.home.domain.QuizRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HomeModule {

    @Binds
    fun bindRepository(impl: QuizRepositoryImpl): QuizRepository
}