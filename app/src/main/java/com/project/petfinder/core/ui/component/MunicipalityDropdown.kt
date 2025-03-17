package com.project.petfinder.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.ChevronDown
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MapPin
import com.project.petfinder.core.domain.model.Municipality

@Composable
fun MunicipalityDropdown(
    selectedMunicipality: Municipality?,
    municipalities: List<Municipality> = emptyList(),
    onMunicipalitySelected: (Municipality) -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    var dropdownExpanded by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = selectedMunicipality?.name ?: "",
        onValueChange = { },
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = enabled) { dropdownExpanded = true },
        placeholder = {
            Text(
                "Selecciona un municipio",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingIcon = {
            Icon(
                Lucide.MapPin,
                contentDescription = "Location",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            Icon(
                Lucide.ChevronDown,
                contentDescription = "Expand",
                tint = Color.Gray
            )
        },
        readOnly = true,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = MaterialTheme.colorScheme.onPrimary
        )
    )

    DropdownMenu(
        expanded = dropdownExpanded,
        onDismissRequest = { dropdownExpanded = false },
        modifier = Modifier.fillMaxWidth(0.9f)
    ) {
        municipalities.forEach { municipality ->
            DropdownMenuItem(
                text = { Text(municipality.name) },
                onClick = {
                    onMunicipalitySelected(municipality)
                    dropdownExpanded = false
                }
            )
        }
    }
}