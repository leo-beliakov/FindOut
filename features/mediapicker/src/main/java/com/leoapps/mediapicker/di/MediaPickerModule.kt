package com.leoapps.mediapicker.di

import com.leoapps.mediapicker.data.MediaRepositoryImpl
import com.leoapps.mediapicker.domain.repository.MediaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MediaPickerModule {

    @Binds
    fun bindRepo(impl: MediaRepositoryImpl): MediaRepository
}