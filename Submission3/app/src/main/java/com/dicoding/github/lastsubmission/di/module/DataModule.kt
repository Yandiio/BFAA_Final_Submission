package com.dicoding.github.lastsubmission.di.module

import com.dicoding.github.lastsubmission.data.network.NetworkService
import com.dicoding.github.lastsubmission.di.DataScope
import com.dicoding.github.lastsubmission.network.Network
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @DataScope
    fun providesNetworkService() : NetworkService {
        return Network.retrofitClient().create(NetworkService::class.java)
    }
}
