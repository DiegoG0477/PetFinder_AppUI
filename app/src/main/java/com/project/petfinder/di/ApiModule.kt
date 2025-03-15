package com.project.petfinder.di

import com.project.petfinder.BuildConfig
import com.project.petfinder.login.data.remote.AuthApiService
import com.project.petfinder.bulletin.data.remote.BulletinApiService
import com.project.petfinder.core.data.remote.LocationApiService
import com.project.petfinder.home.data.remote.PetApiService
import com.project.petfinder.register.data.remote.RegisterApiService
import com.project.petfinder.rescue.data.remote.RescueApiService
import com.project.petfinder.sighting.data.remote.SightingApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL) // Asegúrate de tener esta variable en tu build.gradle
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
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
    fun providePetApiService(retrofit: Retrofit): PetApiService {
        return retrofit.create(PetApiService::class.java)
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
    fun provideBulletinApiService(retrofit: Retrofit): BulletinApiService {
        return retrofit.create(BulletinApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLocationApiService(retrofit: Retrofit): LocationApiService {
        return retrofit.create(LocationApiService::class.java)
    }
}