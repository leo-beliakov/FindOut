package com.leoapps.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.leoapps.form.data.FormDatabase
import com.leoapps.form.data.model.FormEntity
import com.leoapps.form.data.model.converters.QuestionsTypeConverter
import com.leoapps.form.data.model.converters.UriTypeConverter

@Database(
    entities = [FormEntity::class],
    version = 1,
)
@TypeConverters(
    UriTypeConverter::class,
    QuestionsTypeConverter::class
)
abstract class FindOutDatabase : RoomDatabase(), FormDatabase {
    companion object {
        const val DATABASE_NAME = "find_out_database"
    }
}