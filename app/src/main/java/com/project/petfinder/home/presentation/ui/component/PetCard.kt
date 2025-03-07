package com.project.petfinder.home.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.project.petfinder.home.domain.model.Pet
import com.project.petfinder.ui.theme.Montserrat

@Composable
fun PetCard(
    pet: Pet,
    onPetClick: () -> Unit,
    onReportRescue: () -> Unit,
    onReportSighting: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onPetClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF8F0)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Last seen information
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Última vez visto en",
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    color = Color(0xFFBB6835)
                )

                Text(
                    text = "Dueño",
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    color = Color.DarkGray
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${pet.lastSeenLocation}, ${pet.lastSeenDate}",
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )

                Text(
                    text = pet.ownerName,
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Pet image and details
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Pet image
                AsyncImage(
                    model = pet.imageUrl,
                    contentDescription = "Foto de ${pet.name}",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Pet details
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = pet.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFBB6835),
                        fontFamily = Montserrat
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = pet.description,
                        fontSize = 12.sp,
                        fontFamily = Montserrat,
                        color = Color.DarkGray,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onReportRescue,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFFBB6835)
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = SolidColor(Color(0xFFBB6835))
                    )
                ) {
                    Text(
                        text = "Reportar rescate",
                        fontSize = 12.sp,
                        fontFamily = Montserrat
                    )
                }

                OutlinedButton(
                    onClick = onReportSighting,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFFBB6835)
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = SolidColor(Color(0xFFBB6835))
                    )
                ) {
                    Text(
                        text = "Reportar\navistamiento",  // Separamos el texto en dos líneas con \n
                        fontSize = 12.sp,
                        fontFamily = Montserrat,
                        textAlign = TextAlign.Center,     // Centramos el texto
                        modifier = Modifier.fillMaxWidth(), // Hacemos que el Text ocupe todo el ancho disponible
                        lineHeight = 16.sp               // Ajustamos el espaciado entre líneas
                    )
                }
            }
        }
    }
}