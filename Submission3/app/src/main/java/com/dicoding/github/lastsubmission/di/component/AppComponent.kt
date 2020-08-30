package com.dicoding.github.lastsubmission.di.component

import android.app.Application
import com.dicoding.github.lastsubmission.BaseApplication
import com.dicoding.github.lastsubmission.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuilderModule::class,
        AndroidSupportInjectionModule::class,
        RoomModule::class,
        ViewModelModule::class,
        DataModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        FragmentModule::class
    ]

)

interface AppComponent : AndroidInjector<BaseApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent

    }
}