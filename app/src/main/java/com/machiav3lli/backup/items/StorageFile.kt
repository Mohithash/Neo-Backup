package com.machiav3lli.backup.items

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import com.machiav3lli.backup.R
import com.machiav3lli.backup.helpers.FileHelper

object UriHelper {

    fun getMIMEType(context: Context, uri: Uri?): String? {
        return if (uri != null) {
            val scheme = uri.scheme
            if (scheme == null || scheme == ContentResolver.SCHEME_FILE) {
                MIME_TYPE_DIR
            } else if (scheme == ContentResolver.SCHEME_CONTENT) {
                context.contentResolver.getType(uri)
            } else {
                null
            }
        } else {
            null
        }
    }

    fun getFileUri(context: Context, uri: Uri?): Uri? {
        return if (uri != null && DocumentsContract.isDocumentUri(context, uri)) {
            when (uri.authority) {
                DocumentsContract.AUTHORITY -> {
                    val id = DocumentsContract.getDocumentId(uri)
                    val collection = DocumentsContract.buildDocumentUriUsingTree(
                        uri,
                        id
                    )
                    val contentUri = DocumentsContract.buildChildDocumentsUriUsingTree(
                        collection,
                        arrayOf("")
                    )
                    val cursor = context.contentResolver.query(
                        contentUri,
                        arrayOf(MediaStore.Images.Media._ID),
                        null,
                        null,
                        null
                    )
                    if (cursor != null && cursor.moveToFirst()) {
                        val imageId = cursor.getLongOrNull(cursor.getColumnIndex(MediaStore.Images.Media._ID))
                        if (imageId != null) {

