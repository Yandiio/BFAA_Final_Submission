package com.dicoding.github.lastsubmission.data.repository

import com.dicoding.github.lastsubmission.data.db.UserDetails
import com.dicoding.github.lastsubmission.data.db.dao.UserFavoriteDao
import com.dicoding.github.lastsubmission.data.db.entity.UserFavorite
import com.dicoding.github.lastsubmission.data.entity.SearchUserResponse
import com.dicoding.github.lastsubmission.data.entity.UserFollowers
import com.dicoding.github.lastsubmission.data.entity.UserFollowing
import com.dicoding.github.lastsubmission.data.network.NetworkService
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val userFavoriteDao: UserFavoriteDao
) : UserRepository {
    override suspend fun getUserFromAPI(username: String): Response<SearchUserResponse> {
        return networkService.getSearchUsers(username)
    }

    override suspend fun getDetailuserAPI(username: String): Response<UserDetails> {
        return networkService.getDetailUser(username)
    }

    override suspend fun getUserFollowers(username: String): Response<UserFollowers> {
        return networkService.getFollowerUser(username)
    }

    override suspend fun getUserFollowing(username: String): Response<UserFollowing> {
        return networkService.getFollowingUser(username)
    }

    override suspend fun fetchAllUserFavorite(): List<UserFavorite> {
        return userFavoriteDao.fetchAllUsers()
    }

    override suspend fun getFavUserByUsername(username: String): List<UserFavorite> {
        return userFavoriteDao.getFavByUSername(username)
    }

    override suspend fun addUser(favorite: UserFavorite) {
        return userFavoriteDao.addUserToFav(favorite)
    }

    override suspend fun deleteUser(favorite: UserFavorite) {
        return userFavoriteDao.deleteUserFromFav(favorite)
    }


}