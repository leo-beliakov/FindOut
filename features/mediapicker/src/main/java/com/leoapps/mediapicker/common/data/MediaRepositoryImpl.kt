package com.leoapps.mediapicker.common.data

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.leoapps.mediapicker.common.domain.model.Image
import com.leoapps.mediapicker.common.domain.repository.MediaRepository
import com.leoapps.mediapicker.common.utils.photoContentUri
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
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT
        )

        contentResolver.query(
            photoContentUri,
            projection,
            null,
            null,
            null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val widthColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
            val heightColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val contentUri: Uri = Uri.withAppendedPath(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id.toString()
                )

                imageList.add(
                    Image(
                        id = id,
                        uri = contentUri,
                        width = cursor.getInt(widthColumn),
                        height = cursor.getInt(heightColumn),
                    )
                )
            }
        }

        return@withContext imageList
    }
}