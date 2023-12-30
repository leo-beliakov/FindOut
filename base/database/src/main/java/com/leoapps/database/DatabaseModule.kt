package com.leoapps.database

import android.content.Context
import androidx.room.Room
import com.leoapps.database.data.FindOutDatabase
import com.leoapps.form.data.FormDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): FindOutDatabase = Room.databaseBuilder(
        context,
        FindOutDatabase::class.java,
        FindOutDatabase.DATABASE_NAME
    ).build()
}

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseBindsModule {

    @Binds
    fun bindFormDatabase(impl: FindOutDatabase): FormDatabase
}