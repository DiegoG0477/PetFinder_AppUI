package com.project.petfinder.di

import com.project.petfinder.register.data.repository.RegisterRepositoryImpl
import com.project.petfinder.register.domain.repository.RegisterRepository
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