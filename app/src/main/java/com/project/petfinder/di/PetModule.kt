package com.project.petfinder.di

import com.project.petfinder.home.data.repository.PetRepositoryImpl
import com.project.petfinder.home.domain.repository.PetRepository
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