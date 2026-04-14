package com.anz.users_data.di

import com.anz.users_data.repository.DefaultUserRepository
import com.anz.users_domain.repository.UserRepository
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