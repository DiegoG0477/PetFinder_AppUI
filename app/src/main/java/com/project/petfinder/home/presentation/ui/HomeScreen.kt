package com.project.petfinder.home.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.composables.icons.lucide.*
import com.project.petfinder.R
import com.project.petfinder.home.domain.model.Pet
import com.project.petfinder.home.presentation.ui.component.PetCard
import com.project.petfinder.home.presentation.viewmodel.HomeViewModel
import com.project.petfinder.core.ui.theme.Montserrat

@Composable
fun HomeScreen(
    onAddPetClick: () -> Unit,
    onPetClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { HomeTopBar() },
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
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFFBB6835)
                )
            } else if (uiState.pets.isEmpty()) {
                EmptyPetsList(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.pets) { pet: Pet ->
                        PetCard(
                            pet = pet,
                            onPetClick = { onPetClick(pet.id) },
                            onReportRescue = { viewModel.reportRescue(pet.id) },
                            onReportSighting = { viewModel.reportSighting(pet.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeTopBar() {
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
            // Logo
            Image(
                painter = painterResource(id = R.drawable.pet_logo_white),
                contentDescription = "PetFinder Logo",
                modifier = Modifier.size(32.dp)
            )

            // Share icon
            IconButton(onClick = { /* Handle share */ }) {
                Icon(
                    Lucide.Share2,
                    contentDescription = "Compartir",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun EmptyPetsList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Lucide.Search,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = Color(0xFFBB6835)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No hay mascotas reportadas",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Montserrat,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Agrega una mascota perdida tocando el botón +",
            fontSize = 14.sp,
            fontFamily = Montserrat,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}