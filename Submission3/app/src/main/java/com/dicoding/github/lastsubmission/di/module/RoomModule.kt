package com.dicoding.github.lastsubmission.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.dicoding.github.lastsubmission.data.db.AppDatabase
import com.dicoding.github.lastsubmission.data.db.dao.UserFavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    private val mDatabase = "user_favorite_database"

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            mDatabase
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserFavoriteDao(appDatabase : AppDatabase) : UserFavoriteDao {
        return appDatabase.userFavoriteDaobj()
    }
}
