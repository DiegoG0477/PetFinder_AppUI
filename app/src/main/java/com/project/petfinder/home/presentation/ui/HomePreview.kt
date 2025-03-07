package com.project.petfinder.home.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.*
import com.project.petfinder.R
import com.project.petfinder.home.domain.model.Pet
import com.project.petfinder.home.presentation.ui.component.PetCard

@Preview(name = "Home Loading", showBackground = true)
@Composable
fun HomeScreenLoadingPreview() {
    HomeScreenPreview(
        isLoading = true
    )
}

@Preview(name = "Home Vacío", showBackground = true)
@Composable
fun HomeScreenEmptyPreview() {
    HomeScreenPreview(
        isLoading = false,
        pets = emptyList()
    )
}

@Preview(name = "Home con Mascotas", showBackground = true)
@Composable
fun HomeScreenWithPetsPreview() {
    HomeScreenPreview(
        isLoading = false,
        pets = listOf(
            PreviewPet("1", "Max", "Perro Golden Retriever", R.drawable.pet_logo),
            PreviewPet("2", "Luna", "Gato Siames", R.drawable.pet_logo),
            PreviewPet("3", "Rocky", "Perro Bulldog", R.drawable.pet_logo)
        )
    )
}

@Composable
fun HomeScreenPreview(
    isLoading: Boolean = false,
    pets: List<PreviewPet> = emptyList(),
    onAddPetClick: () -> Unit = {},
    onPetClick: (String) -> Unit = {}
) {
    Scaffold(
        topBar = { PreviewHomeTopBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddPetClick,
                containerColor = Color(0xFFF2D2BA),
                contentColor = Color(0xFFBB6835),
                shape = CircleShape,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Lucide.Plus, contentDescription = "Agregar mascota")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFFBB6835)
                )
            } else if (pets.isEmpty()) {
                EmptyPetsList(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(pets) { _, previewPet: PreviewPet ->
                        PetCard(
                            pet = previewPet.toPet(),
                            onPetClick = { onPetClick(previewPet.id) },
                            onReportRescue = {},
                            onReportSighting = {}
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PreviewHomeTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFBB6835))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.pet_logo_white),
                contentDescription = "PetFinder Logo",
                modifier = Modifier.size(32.dp)
            )

            IconButton(onClick = {}) {
                Icon(
                    Lucide.Share2,
                    contentDescription = "Compartir",
                    tint = Color.White
                )
            }
        }
    }
}

// Data class temporal para el preview
data class PreviewPet(
    val id: String,
    val name: String,
    val description: String,
    val photo: Int // Usamos resource ID para el preview
)

// Función de extensión fuera de la clase
fun PreviewPet.toPet(): Pet {


    return Pet(
        id = this.id,
        name = this.name,
        description = "error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. ",
        imageUrl = "",
        ownerName = "Jhon Due",
        lastSeenLocation = "City",
        lastSeenDate = "2024-07-20"
    )
}