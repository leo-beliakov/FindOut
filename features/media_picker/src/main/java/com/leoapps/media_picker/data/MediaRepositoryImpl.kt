package com.leoapps.media_picker.data

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.leoapps.media_picker.domain.model.Photo
import com.leoapps.media_picker.domain.model.Video
import com.leoapps.media_picker.domain.repository.MediaRepository
import com.leoapps.media_picker.utils.photoContentUri
import com.leoapps.media_picker.utils.videoContentUri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : MediaRepository {

    private val contentResolver = context.contentResolver

    override fun queryPhotos(): List<Photo> {
        val photoList = mutableListOf<Photo>()

        val collection = photoContentUri

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE
        )

        val sortOrder = "${MediaStore.Video.Media.DATE_MODIFIED} DESC"

        val query = contentResolver.query(
            collection,
            projection,
            Bundle().apply {
                // Limit & Offset
                putInt(ContentResolver.QUERY_ARG_LIMIT, 1000)
                putInt(ContentResolver.QUERY_ARG_OFFSET, 0)
                // Sort function
                putStringArray(     // <-- This should be an array. I spent a whole day trying to figure out what I was doing wrong
                    ContentResolver.QUERY_ARG_SORT_COLUMNS,
                    arrayOf(MediaStore.Files.FileColumns.DATE_MODIFIED)
                )

                putInt(
                    ContentResolver.QUERY_ARG_SORT_DIRECTION,
                    ContentResolver.QUERY_SORT_DIRECTION_DESCENDING
                )
                // Selection
//                putString(ContentResolver.QUERY_ARG_SQL_SELECTION, selection)
//                putStringArray(
//                    ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS,
//                    selectionArgs
//                )
            },
            null,
//            null,
//            null,
//            sortOrder
        )

        Log.d("MyTag", "query = ${query == null}")
        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val size = cursor.getInt(sizeColumn)

                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                photoList += Photo(contentUri, name, size)
            }
        }

        return photoList
    }

    override fun queryVideos(): List<Video> {
        val videoList = mutableListOf<Video>()

        val collection = videoContentUri

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE
        )

        val sortOrder = "${MediaStore.Video.Media.DATE_MODIFIED} DESC"

        val query = contentResolver.query(
            collection,
            projection,
            null,
            null,
            sortOrder
        )

        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val duration = cursor.getInt(durationColumn)
                val size = cursor.getInt(sizeColumn)

                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                videoList += Video(contentUri, name, duration, size)
            }
        }
        return videoList
    }
}