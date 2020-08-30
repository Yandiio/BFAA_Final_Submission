package com.dicoding.github.lastsubmission.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.github.lastsubmission.core.state.LoaderState
import com.dicoding.github.lastsubmission.domain.UserUseCase
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val __state = MutableLiveData<LoaderState>()
    val state : LiveData<LoaderState>
    get() = __state

    private val __error = MutableLiveData<String>()

    private val _networkError = MutableLiveData<Boolean>()
}