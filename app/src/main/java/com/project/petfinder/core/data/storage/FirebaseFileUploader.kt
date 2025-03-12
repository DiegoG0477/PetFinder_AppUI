package com.project.petfinder.core.data.storage

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class FirebaseFileUploader @Inject constructor(
    private val storage: FirebaseStorage
) : FileUploader {

    override suspend fun uploadFile(uri: Uri): String {
        val filename = UUID.randomUUID().toString()
        val reference = storage.reference.child("images/$filename")

        return try {
            reference.putFile(uri).await()
            reference.downloadUrl.await().toString()
        } catch (e: Exception) {
            throw e
        }
    }
}