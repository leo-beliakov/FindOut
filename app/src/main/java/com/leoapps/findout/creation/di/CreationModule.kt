package com.leoapps.findout.creation.di

import com.leoapps.findout.creation.form.data.FormRepositoryImpl
import com.leoapps.findout.creation.form.domain.FormRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CreationModule {

    @Binds
    @Singleton
    fun bindFormRepository(impl: FormRepositoryImpl): FormRepository
}