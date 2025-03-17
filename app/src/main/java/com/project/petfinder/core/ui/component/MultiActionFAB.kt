package com.project.petfinder.core.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Eye
import com.composables.icons.lucide.Heart
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Pencil
import com.composables.icons.lucide.Plus

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
        modifier = modifier.padding(12.dp),
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Crear Boletín
                ExtendedFloatingActionButton(
                    onClick = {
                        onCreateBulletin()
                        onExpandedChange(false)
                    },
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                ) {
                    Icon(Lucide.Pencil, contentDescription = null)
                    Spacer(Modifier.width(12.dp))
                    Text("Crear Boletín")
                }

                // Reportar Avistamiento
                ExtendedFloatingActionButton(
                    onClick = {
                        onReportSighting()
                        onExpandedChange(false)
                    },
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                ) {
                    Icon(Lucide.Eye, contentDescription = null)
                    Spacer(Modifier.width(12.dp))
                    Text("Reportar Avistamiento")
                }

                // Reportar Rescate
                ExtendedFloatingActionButton(
                    onClick = {
                        onReportRescue()
                        onExpandedChange(false)
                    },
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                ) {
                    Icon(Lucide.Heart, contentDescription = null)
                    Spacer(Modifier.width(12.dp))
                    Text("Reportar Rescate")
                }
            }
        }

        FloatingActionButton(
            onClick = { onExpandedChange(!expanded) },
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
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