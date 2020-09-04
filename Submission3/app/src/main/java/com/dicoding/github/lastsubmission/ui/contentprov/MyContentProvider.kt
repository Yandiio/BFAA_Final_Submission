package com.dicoding.github.lastsubmission.ui.contentprov

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.dicoding.github.lastsubmission.data.db.dao.UserFavoriteDao
import dagger.android.AndroidInjection
import javax.inject.Inject

class MyContentProvider : ContentProvider() {

    companion object {
        private const val USER = 1
        private const val AUTHORITY = "com.dicoding.github.lastsubmission.provider"
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "user_favorite_table", USER)
        }

        @Inject
        lateinit var  userFavoriteDao : UserFavoriteDao

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        throw UnsupportedOperationException()
    }

    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException()
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException()
    }

    override fun onCreate(): Boolean {
        AndroidInjection.inject(this)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return when(sUriMatcher.match(uri)) {
            USER -> userFavoriteDao.getCursorAllFavData()
            else -> null
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        throw UnsupportedOperationException()
    }
}
