package com.leoapps.form.data.model.converters

import androidx.room.TypeConverter
import com.leoapps.form.data.model.QuestionEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class QuestionsTypeConverter {
    @TypeConverter
    fun convertToJsonString(questions: List<QuestionEntity>): String {
        return Json.encodeToString(questions)
    }

    @TypeConverter
    fun convertToObject(json: String): List<QuestionEntity> {
        return Json.decodeFromString(json)
    }
}