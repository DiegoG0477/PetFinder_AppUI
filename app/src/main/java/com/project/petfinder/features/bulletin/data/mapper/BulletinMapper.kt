package com.project.petfinder.features.bulletin.data.mapper

import android.net.Uri
import com.project.petfinder.features.bulletin.data.dto.BulletinDto
import com.project.petfinder.features.bulletin.domain.model.Bulletin
import org.threeten.bp.LocalDate

fun BulletinDto.toDomain(): Bulletin = Bulletin(
    id = this.id,
    petName = this.petName,
    date = LocalDate.parse(this.date),
    municipalityId = this.municipalityId,
    municipalityName = "",
    additionalInfo = this.additionalInfo,
    imageUrl = this.imageUrl?.let { Uri.parse(it) }.toString(),
    userId = ""
)