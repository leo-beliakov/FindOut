package com.leoapps.media_picker.data

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.leoapps.media_picker.domain.model.Image
import com.leoapps.media_picker.domain.repository.MediaRepository
import com.leoapps.media_picker.utils.photoContentUri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : MediaRepository {

    private val contentResolver = context.contentResolver

    override suspend fun queryImages(): List<Image> = withContext(Dispatchers.IO) {
        val imageList = mutableListOf<Image>()
        val projection = arrayOf(MediaStore.Images.Media._ID)

        contentResolver.query(
            photoContentUri,
            projection,
            null,
            null,
            null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val contentUri: Uri = Uri.withAppendedPath(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id.toString()
                )

                imageList.add(
                    Image(
                        id = id,
                        uri = contentUri
                    )
                )
            }
        }

        return@withContext imageList
    }
}