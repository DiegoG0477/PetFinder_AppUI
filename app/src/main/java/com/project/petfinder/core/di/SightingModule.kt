package com.project.petfinder.core.di

import com.project.petfinder.features.sighting.data.repository.SightingRepositoryImpl
import com.project.petfinder.features.sighting.domain.repository.SightingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SightingModule {

    @Binds
    @Singleton
    abstract fun bindSightingRepository(
        impl: SightingRepositoryImpl
    ): SightingRepository
}