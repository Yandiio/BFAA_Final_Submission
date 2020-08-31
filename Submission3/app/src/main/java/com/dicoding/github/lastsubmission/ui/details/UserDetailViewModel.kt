package com.dicoding.github.lastsubmission.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.github.lastsubmission.core.state.LoaderState
import com.dicoding.github.lastsubmission.core.state.ResultState
import com.dicoding.github.lastsubmission.core.util.Coroutine
import com.dicoding.github.lastsubmission.data.db.UserDetails
import com.dicoding.github.lastsubmission.domain.UserUseCase
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {


    /**
     * Loader State
     */
    private val __state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = __state


    /**
     * error
     */
    private val __error = MutableLiveData<String>()

    /**
     * State Network
     */
    private val _networkError = MutableLiveData<Boolean>()

    /**
     * State Followers
     */
    private val _resultUserDetail = MutableLiveData<UserDetails>()
    val resultUserDetail: LiveData<UserDetails>
        get() = _resultUserDetail


    /**
    * From API
    */
    fun getUserDetail(username: String) {
        __state.value = LoaderState.showLoading
        Coroutine.main {
            val result = userUseCase.getUserDetail(username)
            __state.value = LoaderState.closeLoading
            when (result) {
                is ResultState.Success -> {
                    _resultUserDetail.postValue(result.data)
                }
                is ResultState.Error -> {
                    __error.postValue(result.error)
                }
                is ResultState.NetworkError -> {
                    _networkError.postValue(true)
                }
            }
        }
    }
}