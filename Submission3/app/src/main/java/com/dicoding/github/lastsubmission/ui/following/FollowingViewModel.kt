package com.dicoding.github.lastsubmission.ui.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.github.lastsubmission.core.state.LoaderState
import com.dicoding.github.lastsubmission.core.state.ResultState
import com.dicoding.github.lastsubmission.core.util.Coroutine
import com.dicoding.github.lastsubmission.data.entity.UserFollowing
import com.dicoding.github.lastsubmission.data.entity.UserFollowingResponseItem
import com.dicoding.github.lastsubmission.domain.UserUseCase
import javax.inject.Inject

class FollowingViewModel @Inject constructor(private var userUseCase: UserUseCase) : ViewModel() {
    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _error = MutableLiveData<String>()


    private val _resultUserFollowing = MutableLiveData<List<UserFollowingResponseItem>>()
    val resultUserFollowing: LiveData<List<UserFollowingResponseItem>>
        get() = _resultUserFollowing

    fun getResultFollowing(username: String) {
        _state.value = LoaderState.showLoading
        Coroutine.main {
            val result = userUseCase.getUserFollowing(username)
            _state.value = LoaderState.closeLoading
            when(result) {
                is ResultState.Success -> {
                    _resultUserFollowing.postValue(result.data)
                }
                is ResultState.Error -> {
                    _error.postValue(result.error)
                }
            }
        }
    }
}