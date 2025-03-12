package com.project.petfinder.di

import android.content.Context
import android.content.SharedPreferences
import com.project.petfinder.login.data.repository.AuthRepositoryImpl
import com.project.petfinder.login.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    companion object {
        @Provides
        @Singleton
        fun provideSharedPreferences(
            @ApplicationContext context: Context
        ): SharedPreferences {
            return context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        }
    }
}