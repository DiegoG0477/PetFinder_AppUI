package com.project.petfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.petfinder.core.ui.navigation.ComposeNavigationManager
import com.project.petfinder.core.ui.navigation.NavItem
import com.project.petfinder.core.ui.theme.PetFinderTheme
import com.project.petfinder.features.bulletin.presentation.ui.CreateBulletinScreen
import com.project.petfinder.features.home.presentation.ui.HomeScreen
import com.project.petfinder.features.login.presentation.ui.LoginScreen
import com.project.petfinder.features.register.presentation.ui.RegisterScreen
import com.project.petfinder.features.rescue.presentation.ui.ReportRescueScreen
import com.project.petfinder.features.sighting.presentation.ui.ReportSightingScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationManager: ComposeNavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }

        setContent {
            PetFinderTheme(dynamicColor = false) {
                val navController = rememberNavController()

                // Inyectamos el NavController en el NavigationManager
                LaunchedEffect(navController) {
                    navigationManager.setNavController(navController)
                }

                Scaffold { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = NavItem.Home.route,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable(NavItem.Home.route) {
                            HomeScreen(
                                onNavigateToCreateBulletin = {
                                    navController.navigate(NavItem.CreateBoletin.route)
                                },
                                onNavigateToReportSighting = {
                                    navController.navigate(NavItem.ReportarAvistamiento.route)
                                },
                                onNavigateToReportRescue = {
                                    navController.navigate(NavItem.ReportarRescate.route)
                                },
                                onNavigateToLogin = {
                                    navController.navigate(NavItem.Login.route)
                                },
                                onLogout = {
                                    // Implementar lógica de logout
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

                        composable(NavItem.Login.route) {
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate(NavItem.Home.route) {
                                        popUpTo(NavItem.Login.route) { inclusive = true }
                                        launchSingleTop = true
                                    }
                                },
                                onRegisterClick = {
                                    navController.navigate(NavItem.Register.route)
                                }
                            )
                        }

                        composable(NavItem.Register.route) {
                            RegisterScreen(
                                onRegisterSuccess = { navController.navigate(NavItem.Login.route) },
                                onLoginClick = { navController.navigate(NavItem.Login.route) }
                            )
                        }
                    }
                }
            }
        }
    }
}