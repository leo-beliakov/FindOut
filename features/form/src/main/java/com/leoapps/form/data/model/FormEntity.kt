package com.leoapps.form.data.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leoapps.form.data.model.FormEntity.Companion.TABLE_NAME
import com.leoapps.form.domain.model.FormType
import java.util.UUID

@Entity(tableName = TABLE_NAME)
data class FormEntity(
    @PrimaryKey
    val id: UUID,
    val type: FormType,
    val title: String? = null,
    val description: String? = null,
    val coverUri: Uri? = null,
    val questions: List<QuestionEntity> = emptyList()
) {

    companion object {
        const val TABLE_NAME = "form_table"
    }
}