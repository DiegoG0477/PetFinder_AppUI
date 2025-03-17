package com.project.petfinder.features.home.domain.usecase

import com.project.petfinder.features.home.domain.model.Pet
import com.project.petfinder.features.home.domain.repository.PetRepository
import javax.inject.Inject

class GetLostPetsUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    suspend operator fun invoke(): Result<List<Pet>> {
        return petRepository.getLostPets()
    }
}