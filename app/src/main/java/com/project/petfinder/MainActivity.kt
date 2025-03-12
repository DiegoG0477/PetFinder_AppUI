package com.project.petfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.petfinder.core.ui.component.MultiActionFAB
import com.project.petfinder.core.ui.navigation.NavItem
import com.project.petfinder.home.presentation.ui.HomeScreen
import com.project.petfinder.bulletin.presentation.ui.CreateBulletinScreen
import com.project.petfinder.rescue.presentation.ui.ReportRescueScreen
import com.project.petfinder.sighting.presentation.ui.ReportSightingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }

        setContent {
            var expanded by remember { mutableStateOf(false) }
            val navController = rememberNavController()

            Scaffold(
                floatingActionButton = {
                    MultiActionFAB(
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        onCreateBulletin = {
                            navController.navigate(NavItem.CreateBoletin.route)
                            expanded = false
                        },
                        onReportSighting = {
                            navController.navigate(NavItem.ReportarAvistamiento.route)
                            expanded = false
                        },
                        onReportRescue = {
                            navController.navigate(NavItem.ReportarRescate.route)
                            expanded = false
                        }
                    )
                }
            ) { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = NavItem.Home.route,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    composable(NavItem.Home.route) {
                        HomeScreen(
                            onAddPetClick = { expanded = !expanded },
                            onPetClick = { petId ->
                                // Implementar navegación al detalle de la mascota
                            }
                        )
                    }

                    composable(NavItem.CreateBoletin.route) {
                        CreateBulletinScreen(
                            onNavigateBack = {
                                navController.popBackStack()
                            },
                            onBulletinCreated = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(NavItem.ReportarAvistamiento.route) {
                        ReportSightingScreen(
                            onNavigateBack = {
                                navController.popBackStack()
                            },
                            onSightingReported = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(NavItem.ReportarRescate.route) {
                        ReportRescueScreen(
                            onNavigateBack = {
                                navController.popBackStack()
                            },
                            onRescueReported = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}