package com.dicoding.github.lastsubmission.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.github.lastsubmission.core.state.LoaderState
import com.dicoding.github.lastsubmission.core.state.ResultState
import com.dicoding.github.lastsubmission.core.util.Coroutine
import com.dicoding.github.lastsubmission.data.db.entity.UserFavorite
import com.dicoding.github.lastsubmission.data.entity.UserDetails
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
     * User Detail from api
     */
    private val _resultUserDetail = MutableLiveData<UserDetails>()
    val resultUserDetail: LiveData<UserDetails>
        get() = _resultUserDetail

    /**
     * User Detail from DB
     */

    private val _resultUserDetailFromDB = MutableLiveData<List<UserFavorite>>()
    val resultUserDetailFromDb: LiveData<List<UserFavorite>>
        get() = _resultUserDetailFromDB

    /**
     * Add To DB
     */
    private val _resultAddToDb = MutableLiveData<Boolean>()
    val resultAddToDb: LiveData<Boolean>
        get() = _resultAddToDb

    private val _deleteDataFromDb = MutableLiveData<Boolean>()
    val deleteDataFromDb: LiveData<Boolean>
        get() = _deleteDataFromDb


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

    /**
     * Add data Into Database
     */
    fun addDataToUserFav(userFavorite: UserFavorite) {
        Coroutine.main {
            try {
                userUseCase.addDataToFavUser(userFavorite)
                _resultAddToDb.postValue(true)
            } catch (e: Exception) {
                __error.postValue(e.localizedMessage)
            }
        }
    }

    /**
     * Delete data from db
     */

    fun deleteUserDataFromDb(userFavorite: UserFavorite) {
        Coroutine.main {
            try {
                userUseCase.deleteDataFromDb(userFavorite)
                _deleteDataFromDb.postValue(true)
            } catch (e: Exception) {
                __error.postValue(e.localizedMessage)
            }

        }
    }


    fun getDataUserByUsername(username: String) {
        Coroutine.main {
            val result = userUseCase.getDataByUsername(username)
            when (result) {
                is ResultState.Success -> _resultUserDetailFromDB.postValue(result.data)
                is ResultState.Error -> __error.postValue(result.error)
            }
        }
    }
}