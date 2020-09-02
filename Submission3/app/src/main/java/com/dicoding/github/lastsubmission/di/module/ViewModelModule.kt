package com.dicoding.github.lastsubmission.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.github.lastsubmission.ui.details.UserDetailViewModel
import com.dicoding.github.lastsubmission.ui.favorite.FavoriteViewModel
import com.dicoding.github.lastsubmission.ui.followers.FollowersViewModel
import com.dicoding.github.lastsubmission.ui.following.FollowingViewModel
import com.dicoding.github.lastsubmission.ui.main.MainViewModel
import com.dicoding.github.lastsubmission.viewmodel.ViewModelFactory
import com.dicoding.github.lastsubmission.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    internal abstract fun bindUserDetailViewModel(vieModel: UserDetailViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FollowersViewModel::class)
    internal abstract fun bindUserFollowersViewModel(viewModel : FollowersViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FollowingViewModel::class)
    internal abstract fun bindUserFollowingViewModel(viewModel: FollowingViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    internal abstract fun bindUserFavoriteViewModel(viewModel: FavoriteViewModel) : ViewModel


}

