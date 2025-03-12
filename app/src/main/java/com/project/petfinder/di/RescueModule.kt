package com.project.petfinder.di

import com.project.petfinder.rescue.data.repository.RescueRepositoryImpl
import com.project.petfinder.rescue.domain.repository.RescueRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RescueModule {

    @Binds
    @Singleton
    abstract fun bindRescueRepository(
        impl: RescueRepositoryImpl
    ): RescueRepository
}