package com.dicoding.github.lastsubmission.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dicoding.github.lastsubmission.data.db.entity.UserFavorite

@Dao
interface UserFavoriteDao {
    @Query("SELECT * FROM user_favorite_table")
    suspend fun fetchAllUsers() : List<UserFavorite>

    @Query("SELECT * FROM user_favorite_table WHERE username = :userName")
    suspend fun getFavByUSername(userName: String) : List<UserFavorite>

    @Insert
    suspend fun addUserToFav(user: UserFavorite)

    @Delete
    suspend fun deleteUserFromFav(user: UserFavorite)
}