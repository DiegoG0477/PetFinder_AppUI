package com.project.petfinder.core.ui.navigation

import androidx.navigation.NavHostController
import javax.inject.Inject

interface NavigationManager {
    fun navigateTo(route: String)
    fun navigateBack()
}

class ComposeNavigationManager @Inject constructor() : NavigationManager {
    private var navController: NavHostController? = null

    fun setNavController(navController: NavHostController) {
        this.navController = navController
    }

    override fun navigateTo(route: String) {
        navController?.navigate(route)
    }

    override fun navigateBack() {
        navController?.popBackStack()
    }
}