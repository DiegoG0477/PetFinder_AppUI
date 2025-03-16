package com.project.petfinder.core.di

import com.project.petfinder.features.home.data.repository.PetRepositoryImpl
import com.project.petfinder.features.home.domain.repository.PetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PetModule {

    @Binds
    abstract fun bindPetRepository(
        impl: PetRepositoryImpl
    ): PetRepository
}