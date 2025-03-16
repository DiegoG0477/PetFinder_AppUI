package com.project.petfinder.bulletin.data.dto.mapper

import android.net.Uri
import com.project.petfinder.bulletin.data.dto.BulletinDto
import com.project.petfinder.bulletin.domain.model.Bulletin
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