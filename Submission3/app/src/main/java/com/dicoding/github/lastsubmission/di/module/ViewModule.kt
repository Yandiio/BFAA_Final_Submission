package com.dicoding.github.lastsubmission.di.module

import com.dicoding.github.lastsubmission.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelFactory
}
