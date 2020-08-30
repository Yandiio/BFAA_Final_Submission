package com.dicoding.github.lastsubmission.di.module

import com.dicoding.github.lastsubmission.ui.followers.FollowersFragment
import com.dicoding.github.lastsubmission.ui.following.FollowingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contriburteFollowingFragment() : FollowingFragment

    @ContributesAndroidInjector
    abstract fun contributFollowersFragment() : FollowersFragment
}
