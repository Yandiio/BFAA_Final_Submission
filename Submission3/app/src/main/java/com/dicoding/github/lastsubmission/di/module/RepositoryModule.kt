package com.dicoding.github.lastsubmission.di.module

import com.dicoding.github.lastsubmission.data.db.dao.UserFavoriteDao
import com.dicoding.github.lastsubmission.data.network.NetworkService
import com.dicoding.github.lastsubmission.data.repository.UserRepository
import com.dicoding.github.lastsubmission.data.repository.UserRepositoryImpl
import com.dicoding.github.lastsubmission.di.DataScope
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class, RoomModule::class])
class RepositoryModule {

    @Provides
    fun provideUserRepository(
        @DataScope service : NetworkService,
        userFavoriteDao: UserFavoriteDao
    ) : UserRepository {
        return UserRepositoryImpl(service, userFavoriteDao)
    }
}
