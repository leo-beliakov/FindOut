package com.leoapps.form.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leoapps.form.data.model.FormEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FormDao {

    @Query("SELECT * FROM ${FormEntity.TABLE_NAME}")
    fun getAllFormsAsFlow(): Flow<List<FormEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveForm(form: FormEntity)

    @Query("DELETE FROM ${FormEntity.TABLE_NAME} WHERE id = :id")
    suspend fun deleteFormById(id: Long)
}