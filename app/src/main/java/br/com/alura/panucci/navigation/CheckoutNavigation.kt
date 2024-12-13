package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alura.panucci.ui.screens.CheckoutScreen
import br.com.alura.panucci.ui.viewmodels.CheckoutViewModel

private const val checkoutRoute = "checkout"

fun NavGraphBuilder.checkoutScreen(
    onPopBackStack: () -> Unit
) {
    composable(checkoutRoute) {
        val viewModel: CheckoutViewModel = viewModel()
        val uiSate by viewModel.uiState.collectAsState()
        CheckoutScreen(
            onOrderClick = onPopBackStack,
            uiState = uiSate
        )
    }
}

fun NavController.navigateToCheckout() {
    navigate(checkoutRoute)
}