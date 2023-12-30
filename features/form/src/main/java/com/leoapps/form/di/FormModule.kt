package com.leoapps.form.di

import com.leoapps.form.data.FormDatabase
import com.leoapps.form.data.FormRepositoryImpl
import com.leoapps.form.domain.FormRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface FormBindsModule {

    @Binds
    fun bindsRepository(impl: FormRepositoryImpl): FormRepository
}

@Module
@InstallIn(SingletonComponent::class)
internal object FormModule{

    @Provides
    fun providesFormDao(db: FormDatabase) = db.getFormDao()
}