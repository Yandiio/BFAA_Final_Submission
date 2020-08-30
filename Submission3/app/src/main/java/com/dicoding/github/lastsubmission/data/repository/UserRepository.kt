package com.dicoding.github.lastsubmission.data.repository

import com.dicoding.github.lastsubmission.data.db.UserDetails
import com.dicoding.github.lastsubmission.data.db.entity.UserFavorite
import com.dicoding.github.lastsubmission.data.entity.*
import retrofit2.Response

interface UserRepository {
    /**
     *  Fetch API
     */

    suspend fun getUserFromAPI(username: String): Response<SearchUserResponse>

    suspend fun getDetailuserAPI(username: String): Response<UserDetails>

    suspend fun getUserFollowers(username: String): Response<UserFollowers>

    suspend fun getUserFollowing(username: String): Response<UserFollowing>

    /**
     *  Fetch Local
     */

    suspend fun fetchAllUserFavorite(): List<UserFavorite>

    suspend fun getFavUserByUsername(username: String): List<UserFavorite>

    suspend fun addUser(favorite: UserFavorite)

    suspend fun deleteUser(favorite: UserFavorite)

}