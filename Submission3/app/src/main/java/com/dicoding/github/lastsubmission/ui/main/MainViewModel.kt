package com.dicoding.github.lastsubmission.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.github.lastsubmission.core.state.LoaderState
import com.dicoding.github.lastsubmission.core.state.ResultState
import com.dicoding.github.lastsubmission.core.util.Coroutine
import com.dicoding.github.lastsubmission.data.entity.UserSearchResponseItem
import com.dicoding.github.lastsubmission.domain.UserUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {
    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    /**
     * Error
     */
    private val _error = MutableLiveData<String>()


    /**
     * Network Error
     */
    private val _networkError = MutableLiveData<Boolean>()
    val networkError : LiveData<Boolean>
        get() = _networkError

    /**
     * Fetch Result from API
     */

    private val _resultFromApi = MutableLiveData<List<UserSearchResponseItem>>()
    val resultFromApi : LiveData<List<UserSearchResponseItem>>
        get() = _resultFromApi

    fun getUserFromApi(query: String) {
        _state.value = LoaderState.showLoading
        Coroutine.main {
            val result = userUseCase.getUserFromApi(query)
            _state.value = LoaderState.closeLoading
            when(result) {
                is ResultState.Success -> {
                    _resultFromApi.postValue(result.data?.userItems)
                }
                is ResultState.Error -> {
                    _error.postValue(result.error)
                }
                is ResultState.NetworkError -> {
                    _networkError.postValue(true)
                }
            }
        }
    }

}
