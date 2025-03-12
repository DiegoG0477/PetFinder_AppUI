package com.project.petfinder.core.data.storage

import android.net.Uri

interface FileUploader {
    suspend fun uploadFile(uri: Uri): String
}