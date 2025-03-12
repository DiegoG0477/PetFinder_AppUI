package com.project.petfinder.di

import com.google.firebase.storage.FirebaseStorage
import com.project.petfinder.core.data.storage.FileUploader
import com.project.petfinder.core.data.storage.FirebaseFileUploader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun provideFileUploader(impl: FirebaseFileUploader): FileUploader = impl
}