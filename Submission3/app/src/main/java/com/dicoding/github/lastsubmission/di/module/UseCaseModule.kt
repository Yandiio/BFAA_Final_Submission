package com.dicoding.github.lastsubmission.di.module

import com.dicoding.github.lastsubmission.data.repository.UserRepository
import com.dicoding.github.lastsubmission.domain.UserUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
class UseCaseModule {

    @Provides
    fun providesUseCaseModule(
        userRepository: UserRepository
    ) : UserUseCase {
        return UserUseCase(userRepository)
    }
}
