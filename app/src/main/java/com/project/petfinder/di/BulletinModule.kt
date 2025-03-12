package com.project.petfinder.di

import com.project.petfinder.bulletin.data.repository.BulletinRepositoryImpl
import com.project.petfinder.bulletin.domain.repository.BulletinRepository
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