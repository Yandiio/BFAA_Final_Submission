package com.dicoding.github.lastsubmission.di.module

import com.dicoding.github.lastsubmission.ui.details.UserDetailActivity
import com.dicoding.github.lastsubmission.ui.favorite.FavoriteActivity
import com.dicoding.github.lastsubmission.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity() : UserDetailActivity

    @ContributesAndroidInjector
    abstract fun contributeFavoriteActivity() : FavoriteActivity
}