package com.project.petfinder.core.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.*

@Composable
fun MultiActionFAB(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onCreateBulletin: () -> Unit,
    onReportSighting: () -> Unit,
    onReportRescue: () -> Unit,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(targetState = expanded, label = "FAB transition")
    val rotation by transition.animateFloat(label = "FAB rotation") { state ->
        if (state) 45f else 0f
    }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Crear Boletín
                ExtendedFloatingActionButton(
                    onClick = {
                        onCreateBulletin()
                        onExpandedChange(false)
                    },
                    containerColor = Color(0xFFF2D2BA),
                    contentColor = Color(0xFFBB6835),
                ) {
                    Icon(Lucide.Pencil, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Crear Boletín")
                }

                // Reportar Avistamiento
                ExtendedFloatingActionButton(
                    onClick = {
                        onReportSighting()
                        onExpandedChange(false)
                    },
                    containerColor = Color(0xFFF2D2BA),
                    contentColor = Color(0xFFBB6835),
                ) {
                    Icon(Lucide.Eye, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Reportar Avistamiento")
                }

                // Reportar Rescate
                ExtendedFloatingActionButton(
                    onClick = {
                        onReportRescue()
                        onExpandedChange(false)
                    },
                    containerColor = Color(0xFFF2D2BA),
                    contentColor = Color(0xFFBB6835),
                ) {
                    Icon(Lucide.Heart, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Reportar Rescate")
                }
            }
        }

        FloatingActionButton(
            onClick = { onExpandedChange(!expanded) },
            containerColor = Color(0xFFF2D2BA),
            contentColor = Color(0xFFBB6835),
            shape = CircleShape
        ) {
            Icon(
                Lucide.Plus,
                contentDescription = "Más opciones",
                modifier = Modifier.rotate(rotation)
            )
        }
    }
}