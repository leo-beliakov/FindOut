package com.leoapps.creation.di

import com.leoapps.creation.form.data.FormCreationRepositoryImpl
import com.leoapps.creation.form.domain.FormCreationRepository
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
    fun bindFormRepository(impl: FormCreationRepositoryImpl): FormCreationRepository
}