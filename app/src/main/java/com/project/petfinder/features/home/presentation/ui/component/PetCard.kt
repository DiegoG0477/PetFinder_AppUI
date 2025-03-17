package com.project.petfinder.features.home.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
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
import com.project.petfinder.core.ui.theme.Montserrat
import com.project.petfinder.features.home.domain.model.Pet

@Composable
fun PetCard(
    pet: Pet,
    onReportRescue: () -> Unit,
    onReportSighting: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
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
                    color = MaterialTheme.colorScheme.primary
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
                        color = MaterialTheme.colorScheme.primary,
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
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = SolidColor(MaterialTheme.colorScheme.primary)
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
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = SolidColor(MaterialTheme.colorScheme.primary)
                    )
                ) {
                    Text(
                        text = "Reportar\navistamiento",
                        fontSize = 12.sp,
                        fontFamily = Montserrat,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}