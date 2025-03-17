package com.project.petfinder.core.ui.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.project.petfinder.core.ui.theme.Montserrat

@Composable
fun ImageSelector(
    selectedImageUri: Uri?,
    onImageSelected: (Uri) -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    contentDescription: String = "Selected image",
    uploadButtonText: String = "Subir imagen",
    changeButtonText: String = "Cambiar imagen"
) {
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { onImageSelected(it) }
    }

    if (selectedImageUri != null) {
        AsyncImage(
            model = selectedImageUri,
            contentDescription = contentDescription,
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }

    TextButton(
        onClick = { imagePickerLauncher.launch("image/*") },
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = if (selectedImageUri == null) uploadButtonText else changeButtonText,
            fontSize = 14.sp,
            fontFamily = Montserrat
        )
    }
}