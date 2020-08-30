package com.dicoding.github.lastsubmission.di.module

import com.dicoding.github.lastsubmission.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity
}