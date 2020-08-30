package com.dicoding.github.lastsubmission.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.github.lastsubmission.data.db.dao.UserFavoriteDao
import com.dicoding.github.lastsubmission.data.db.entity.UserFavorite

@Database(
    entities = [UserFavorite::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userFavoriteDaobj() : UserFavoriteDao
}