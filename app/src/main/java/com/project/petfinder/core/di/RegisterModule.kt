package com.project.petfinder.core.di

import com.project.petfinder.features.register.data.repository.RegisterRepositoryImpl
import com.project.petfinder.features.register.domain.repository.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RegisterModule {

    @Binds
    abstract fun bindRegisterRepository(
        impl: RegisterRepositoryImpl
    ): RegisterRepository
}