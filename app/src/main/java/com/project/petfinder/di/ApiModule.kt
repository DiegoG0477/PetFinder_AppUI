package com.project.petfinder.di

import com.project.petfinder.bulletin.data.BulletinApiService
import com.project.petfinder.core.data.LocationApiService
import com.project.petfinder.home.data.PetApiService
import com.project.petfinder.rescue.data.RescueApiService
import com.project.petfinder.login.data.AuthApiService
import com.project.petfinder.register.data.RegisterApiService
import com.project.petfinder.sighting.data.SightingApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideBulletinApiService(retrofit: Retrofit): BulletinApiService {
        return retrofit.create(BulletinApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePetApiService(retrofit: Retrofit): PetApiService {
        return retrofit.create(PetApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRegisterApiService(retrofit: Retrofit): RegisterApiService {
        return retrofit.create(RegisterApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRescueApiService(retrofit: Retrofit): RescueApiService {
        return retrofit.create(RescueApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSightingApiService(retrofit: Retrofit): SightingApiService {
        return retrofit.create(SightingApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLocationApiService(retrofit: Retrofit): LocationApiService {
        return retrofit.create(LocationApiService::class.java)
    }
}