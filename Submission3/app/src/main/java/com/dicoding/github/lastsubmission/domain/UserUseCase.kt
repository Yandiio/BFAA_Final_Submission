package com.dicoding.github.lastsubmission.domain

import com.dicoding.github.lastsubmission.core.state.ResultState
import com.dicoding.github.lastsubmission.core.util.safeApiCall
import com.dicoding.github.lastsubmission.data.entity.UserDetails
import com.dicoding.github.lastsubmission.data.db.entity.UserFavorite
import com.dicoding.github.lastsubmission.data.entity.SearchUserResponse
import com.dicoding.github.lastsubmission.data.entity.UserFollowers
import com.dicoding.github.lastsubmission.data.entity.UserFollowing
import com.dicoding.github.lastsubmission.data.repository.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun getUserFromApi(username: String): ResultState<SearchUserResponse> {
        return safeApiCall {
            val response = userRepository.getUserFromAPI(username)
            try {
                ResultState.Success(response.body())
            } catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getUserFollowers(username: String): ResultState<UserFollowers> {
        return safeApiCall {
            val response = userRepository.getUserFollowers(username)
            try {
                ResultState.Success(response.body())
            } catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getUserDetail(username: String): ResultState<UserDetails> {
        return safeApiCall {
            val response = userRepository.getDetailuserAPI(username)
            try {
                ResultState.Success(response.body())
            } catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getUserFollowing(username: String): ResultState<UserFollowing> {
        return safeApiCall {
            val response = userRepository.getUserFollowing(username)
            try {
                ResultState.Success(response.body())
            } catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun fetchUserFavorites(): ResultState<List<UserFavorite>> {
        return try {
            val result = userRepository.fetchAllUserFavorite()
            ResultState.Success(result)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun addDataToFavUser(userFav: UserFavorite) {
        return try {
            userRepository.addUser(userFav)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun getDataByUsername(username: String) : ResultState<List<UserFavorite>> {
        return try {
            val result = userRepository.getFavUserByUsername(username)
            ResultState.Success(result)
        } catch (e: Exception) {
            ResultState.Error(e.localizedMessage, 500)
        }
    }

    suspend fun deleteDataFromDb(userFav: UserFavorite?) {
        return try {
            if (userFav != null) {
                userRepository.deleteUser(userFav)
            } else {

            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}