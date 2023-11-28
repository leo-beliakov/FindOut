package com.leoapps.mediapicker.common.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.net.toUri
import com.leoapps.mediapicker.common.domain.model.Image
import com.leoapps.mediapicker.common.domain.repository.MediaRepository
import com.leoapps.mediapicker.common.utils.photoContentUri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : MediaRepository {

    // https://developer.android.com/training/data-storage/app-specific#media
    private val imagesFolder = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

    private val contentResolver = context.contentResolver

    override suspend fun queryImages(): List<Image> = withContext(Dispatchers.IO) {
        val imageList = mutableListOf<Image>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )
        val sortOrder = "${MediaStore.Video.Media.DATE_TAKEN} DESC"

        contentResolver.query(
            photoContentUri,
            projection,
            null,
            null,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val widthColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
            val heightColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)
            val albumIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
            val albumColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

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
                        albumId = cursor.getLong(albumIdColumn),
                        albumName = cursor.getString(albumColumn)
                    )
                )
            }
        }

        return@withContext imageList
    }

    override suspend fun copyImageToInternalStorage(image: Image): Uri? =
        withContext(Dispatchers.IO) {
            try {
                contentResolver.openInputStream(image.uri)?.use { inputStream ->
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    val outputFile = getOutputFile(image)
                    val outputStream = FileOutputStream(outputFile)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    return@withContext outputFile.toUri()
                }

                return@withContext null
            } catch (e: IOException) {
                return@withContext null
            }
        }

    private fun getOutputFile(image: Image): File {
        val appDirectory = File(imagesFolder, "FindOut")
        if (!appDirectory.exists()) {
            appDirectory.mkdirs()
        }

        return File(appDirectory, "${image.id}.jpg")
    }
}