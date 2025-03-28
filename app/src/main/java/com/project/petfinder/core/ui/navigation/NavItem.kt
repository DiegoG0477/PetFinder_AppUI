package com.project.petfinder.core.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.composables.icons.lucide.Eye
import com.composables.icons.lucide.Heart
import com.composables.icons.lucide.House
import com.composables.icons.lucide.LogIn
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.PawPrint

sealed class NavItem(
    val icon: ImageVector,
    val label: String,
    val route: String
) {
    object Home : NavItem(
        icon = Lucide.House,
        label = "Inicio",
        route = "home"
    )

    object CreateBoletin : NavItem(
        icon = Lucide.PawPrint,
        label = "Crear Boletín",
        route = "create_boletin"
    )

    object ReportarAvistamiento : NavItem(
        icon = Lucide.Eye,
        label = "Reportar Avistamiento",
        route = "report_avistamiento"
    )

    object ReportarRescate : NavItem(
        icon = Lucide.Heart,
        label = "Reportar Rescate",
        route = "report_rescate"
    )

    object Login : NavItem(
        icon = Lucide.LogIn,
        label = "Iniciar sesión",
        route = "login"
    )

    object Register : NavItem(
        icon = Lucide.LogIn,
        label = "Registrarse",
        route = "register"
    )
}