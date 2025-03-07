package com.project.petfinder.home.domain.repository

import com.project.petfinder.home.domain.model.Pet

interface PetRepository {
    suspend fun getLostPets(): List<Pet>
    suspend fun reportRescue(petId: String)
    suspend fun reportSighting(petId: String)
}