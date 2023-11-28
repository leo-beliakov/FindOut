package com.leoapps.mediapicker.common.di

import com.leoapps.mediapicker.common.data.MediaRepositoryImpl
import com.leoapps.mediapicker.common.domain.repository.MediaRepository
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