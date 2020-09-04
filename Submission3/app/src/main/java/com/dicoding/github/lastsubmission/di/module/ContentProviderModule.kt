package com.dicoding.github.lastsubmission.di.module

import com.dicoding.github.lastsubmission.ui.contentprov.MyContentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes =  [RoomModule::class])
abstract class ContentProviderModule {

    @ContributesAndroidInjector
    abstract fun contributeContentProvider() : MyContentProvider
}