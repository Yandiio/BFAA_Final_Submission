package com.dicoding.github.lastsubmission.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.github.lastsubmission.core.state.ResultState
import com.dicoding.github.lastsubmission.core.util.Coroutine
import com.dicoding.github.lastsubmission.data.db.entity.UserFavorite
import com.dicoding.github.lastsubmission.domain.UserUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val __error = MutableLiveData<String>()

    private val _resultUserDb = MutableLiveData<List<UserFavorite>>()
    val resultUserDb: LiveData<List<UserFavorite>>
        get() = _resultUserDb

    init {
        fetchAllUserFavorite()
    }

    fun fetchAllUserFavorite() {
        Coroutine.main {
            when (val result = userUseCase.fetchUserFavorites()) {
                is ResultState.Success -> _resultUserDb.postValue(result.data)
                is ResultState.Error -> __error.postValue(result.error)
            }
        }
    }
}
