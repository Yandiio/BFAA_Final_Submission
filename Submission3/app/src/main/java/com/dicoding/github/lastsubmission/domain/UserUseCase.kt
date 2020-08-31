package com.dicoding.github.lastsubmission.domain

import com.dicoding.github.lastsubmission.core.state.ResultState
import com.dicoding.github.lastsubmission.core.util.safeApiCall
import com.dicoding.github.lastsubmission.data.db.UserDetails
import com.dicoding.github.lastsubmission.data.entity.SearchUserResponse
import com.dicoding.github.lastsubmission.data.entity.UserFollowers
import com.dicoding.github.lastsubmission.data.entity.UserFollowing
import com.dicoding.github.lastsubmission.data.repository.UserRepository
import java.lang.Exception
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun getUserFromApi(username: String) : ResultState<SearchUserResponse> {
        return safeApiCall {
            val response = userRepository.getUserFromAPI(username)
            try {
                ResultState.Success(response.body())
            } catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getUserFollowers(username: String) : ResultState<UserFollowers> {
        return safeApiCall {
            val response = userRepository.getUserFollowers(username)
            try {
                ResultState.Success(response.body())
            } catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getUserDetail(username: String) : ResultState<UserDetails> {
        return safeApiCall {
            val response = userRepository.getDetailuserAPI(username)
            try {
                ResultState.Success(response.body())
            }catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getUserFollowing(username: String) : ResultState<UserFollowing> {
        return safeApiCall {
            val response = userRepository.getUserFollowing(username)
            try {
                ResultState.Success(response.body())
            } catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }
}