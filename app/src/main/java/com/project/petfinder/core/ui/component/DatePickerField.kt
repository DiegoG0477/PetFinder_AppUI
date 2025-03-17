package com.project.petfinder.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.composables.icons.lucide.Calendar
import com.composables.icons.lucide.Lucide
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@Composable
fun DatePickerField(
    date: LocalDate,
    onDateChanged: (LocalDate) -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val dateFormatter = remember { DateTimeFormatter.ofPattern("dd/MM/yyyy") }

    FormField(
        label = "Fecha",
        value = date.format(dateFormatter),
        onValueChange = { },
        placeholder = "DD/MM/YYYY",
        leadingIcon = { Icon(Lucide.Calendar, "Date", tint = Color.Gray) },
        readOnly = true,
        enabled = enabled,
        modifier = modifier.clickable(enabled = enabled) { showDatePicker = true }
    )

    if (showDatePicker) {
        DatePicker(
            onDismissRequest = { showDatePicker = false },
            onDateSelected = { selectedDate ->
                onDateChanged(selectedDate)
                showDatePicker = false
            },
            selectedDate = date
        )
    }
}