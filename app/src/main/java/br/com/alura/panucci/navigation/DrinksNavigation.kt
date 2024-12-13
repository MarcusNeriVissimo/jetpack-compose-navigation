package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.model.Product
import br.com.alura.panucci.ui.screens.DrinksListScreen
import br.com.alura.panucci.ui.viewmodels.DrinksListViewModel

internal const val drinksRoute = "drinks"

fun NavGraphBuilder.drinksScreen(
    onNavigateToProductDetails: (Product) -> Unit
) {
    composable(drinksRoute) {
        val viewModel: DrinksListViewModel = viewModel()
        val uiSate by viewModel.uiState.collectAsState()
        DrinksListScreen(
            onProductClick = onNavigateToProductDetails,
            uiSate = uiSate
        )
    }
}

fun NavController.navigateToDrinks(navOptions: NavOptions? = null){
    navigate(drinksRoute, navOptions)
}