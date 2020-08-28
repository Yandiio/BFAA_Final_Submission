package com.dicoding.github.lastsubmission.core.state

sealed class LoaderState {
    object showLoading: LoaderState()
    object closeLoading: LoaderState()
}