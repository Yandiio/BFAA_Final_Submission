package com.dicoding.github.lastsubmission.ui.followers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.github.lastsubmission.core.state.LoaderState
import com.dicoding.github.lastsubmission.core.state.ResultState
import com.dicoding.github.lastsubmission.core.util.Coroutine
import com.dicoding.github.lastsubmission.data.entity.UserFollowersResponseItem
import com.dicoding.github.lastsubmission.domain.UserUseCase
import javax.inject.Inject

class FollowersViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    /**
     * Loader State
     */
    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    /**
     * Error state
     */

    private val _error = MutableLiveData<String>()

    /**
     * Network Error
     */

    private val __networkError = MutableLiveData<Boolean>()

    /**
     * State Followers
     */

    private val _resultUserFollower = MutableLiveData<List<UserFollowersResponseItem>>()
    val resultUserFollowers: LiveData<List<UserFollowersResponseItem>>
        get() = _resultUserFollower


    fun getUserFollowers(username: String) {
        _state.value = LoaderState.showLoading
        Coroutine.main {
            val result = userUseCase.getUserFollowers(username)
            _state.value = LoaderState.closeLoading
            when (result) {
                is ResultState.Success -> {
                    _resultUserFollower.postValue(result.data)
                }
                is ResultState.Error -> {
                    _error.postValue(result.error)
                }
                is ResultState.NetworkError -> {
                    __networkError.postValue(true)
                }
            }
        }
    }

}