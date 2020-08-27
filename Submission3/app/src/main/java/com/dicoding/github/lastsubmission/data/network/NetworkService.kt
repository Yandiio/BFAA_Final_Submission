package com.dicoding.github.lastsubmission.data.network

import com.dicoding.github.lastsubmission.data.db.UserDetails
import com.dicoding.github.lastsubmission.data.entity.SearchUserResponse
import com.dicoding.github.lastsubmission.data.entity.UserFollowers
import com.dicoding.github.lastsubmission.data.entity.UserFollowersResponseItem
import com.dicoding.github.lastsubmission.data.entity.UserFollowing
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    /**
     * Endpoint Search Users
     */
    @GET("search/users?")
    suspend fun getSearchUsers(
        @Query("q") q: String
    ): Response<SearchUserResponse>

    /**
     * Endpoint Detail of User
     */
    @GET("users/{username}")
    @Headers("Authorization: token 4dda8eb575a5df4bbb627e11e0563afcdb3ab95a ")
    suspend fun getDetailUser(
        @Path("username") username: String
    ): Response<UserDetails>

    /**
     * Endpoint Followers
     */
    @GET("users/{username}/followers")
    suspend fun getFollowerUser(
        @Path("username") username: String
    ): Response<UserFollowers>

    /**
     * Endpoint following
     */
    @GET("users/{username}/following")
    suspend fun getFollowingUser(
        @Path("username") username: String
    ): Response<UserFollowing>
}