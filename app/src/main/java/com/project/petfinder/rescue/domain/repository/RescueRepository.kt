package com.project.petfinder.rescue.domain.repository

import android.net.Uri
import com.project.petfinder.rescue.domain.model.Rescue
import org.threeten.bp.LocalDate

interface RescueRepository {
    /**
     * Reports a new rescue of a pet
     * @param petId The ID of the pet that was rescued
     * @param date The date when the pet was found
     * @param municipalityId The ID of the municipality where the pet was found
     * @param additionalInfo Additional information about the rescue
     * @param imageUri The URI of the image showing the rescued pet
     * @return The created Rescue object
     * @throws Exception if the operation fails
     */
    suspend fun reportRescue(
        petId: String,
        date: LocalDate,
        municipalityId: String,
        additionalInfo: String,
        imageUri: Uri?
    ): Rescue

    /**
     * Gets a rescue by its ID
     * @param rescueId The ID of the rescue to fetch
     * @return The Rescue object if found
     * @throws Exception if the rescue is not found or if the operation fails
     */
    suspend fun getRescueById(rescueId: String): Rescue

    /**
     * Gets all rescues for a specific pet
     * @param petId The ID of the pet
     * @return List of rescues associated with the pet
     */
    suspend fun getRescuesForPet(petId: String): List<Rescue>

    /**
     * Gets all rescues reported by a specific user
     * @param userId The ID of the user
     * @return List of rescues reported by the user
     */
    suspend fun getRescuesByUser(userId: String): List<Rescue>
}