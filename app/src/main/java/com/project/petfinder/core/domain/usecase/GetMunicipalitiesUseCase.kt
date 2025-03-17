package com.project.petfinder.core.domain.usecase

import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.domain.repository.LocationRepository
import javax.inject.Inject

class GetMunicipalitiesUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): List<Municipality> {
        return locationRepository.getMunicipalities()
    }
}