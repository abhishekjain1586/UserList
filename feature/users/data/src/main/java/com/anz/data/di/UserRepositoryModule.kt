package com.anz.data.di

import com.anz.data.repository.DefaultUserRepository
import com.anz.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsUserRepository(
        defaultUserRepository: DefaultUserRepository
    ): UserRepository
}