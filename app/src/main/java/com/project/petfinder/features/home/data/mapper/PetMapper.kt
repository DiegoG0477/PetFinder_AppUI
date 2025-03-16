package com.project.petfinder.features.home.data.mapper

import com.project.petfinder.features.home.data.dto.PetDto
import com.project.petfinder.features.home.domain.model.Pet

fun PetDto.toDomain(): Pet = Pet(
    id = this.id,
    name = this.name,
    description = this.description ?: "",
    imageUrl = this.imageUrl,
    ownerName = this.ownerName ?: "",
    lastSeenLocation = this.lastSeenLocation,
    lastSeenDate = this.lastSeenDate ?: ""
)