package com.project.petfinder.core.di

import com.project.petfinder.features.bulletin.data.repository.BulletinRepositoryImpl
import com.project.petfinder.features.bulletin.domain.repository.BulletinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BulletinModule {

    @Binds
    abstract fun bindBulletinRepository(
        impl: BulletinRepositoryImpl
    ): BulletinRepository
}