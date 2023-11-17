package com.leoapps.media_picker.di

import com.leoapps.media_picker.data.MediaRepositoryImpl
import com.leoapps.media_picker.domain.repository.MediaRepository
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