package com.leoapps.form.data.model.converters

import android.net.Uri
import androidx.room.TypeConverter

class UriTypeConverter {

    @TypeConverter
    fun fromObjectToString(uri: Uri?): String {
        return uri.toString()
    }

    @TypeConverter
    fun fromStringToObject(str: String): Uri? {
        return Uri.parse(str)
    }
}