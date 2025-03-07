package com.project.petfinder.core.domain.repository

import com.project.petfinder.core.domain.model.Municipality

interface LocationRepository {
    suspend fun getMunicipalities(): List<Municipality>
}