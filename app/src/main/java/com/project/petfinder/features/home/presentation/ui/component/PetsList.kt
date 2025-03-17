package com.project.petfinder.features.home.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.project.petfinder.features.home.domain.model.Pet

@Composable
fun PetsList(
    pets: List<Pet>,
    onReportRescue: (String) -> Unit,
    onReportSighting: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pets) { pet ->
            PetCard(
                pet = pet,
                onReportRescue = { onReportRescue(pet.id) },
                onReportSighting = { onReportSighting(pet.id) }
            )
        }
    }
}