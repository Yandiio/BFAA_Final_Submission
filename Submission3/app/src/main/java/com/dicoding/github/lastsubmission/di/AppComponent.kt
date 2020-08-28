package com.dicoding.github.lastsubmission.di

import android.app.Application
import com.dicoding.github.lastsubmission.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuilderModule::class,
        AndroidSupportInjectionModule::class
    ]

)

interface AppComponent : AndroidInjector<BaseApplication> {

    interface builder {
        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent

    }
}