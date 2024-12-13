package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navOptions
import br.com.alura.panucci.model.Product
import br.com.alura.panucci.ui.components.BottomAppBarItem

internal const val homeGraphRoute = "home"

fun NavGraphBuilder.homeGraph(
    onNavigateToCheckout: () -> Unit,
    onNavigateToProductDetails: (Product) -> Unit,
    onNavigateToHighlights: () -> Unit
) {
    navigation(
        startDestination = highlistsListRoute,
        route = homeGraphRoute
    ) {
        highlightsListScreen(
            onNavigateToCheckout,
            onNavigateToProductDetails,
            onNavigateToHighlights
        )
        menuScreen(onNavigateToProductDetails)
        drinksScreen(onNavigateToProductDetails)
        authenticationScreen(onNavigateToHighlights)
    }
}

fun NavController.navigateToHomeGraph(){
    navigate(homeGraphRoute)
}

fun NavController.navigateSingleTopWithPopUpTo(
    item: BottomAppBarItem
) {
    val (route, navigate) = when (item) {
        BottomAppBarItem.drinks -> Pair(
            drinksRoute,
            ::navigateToDrinks
        )

        BottomAppBarItem.highlightsList -> Pair(
            highlistsListRoute,
            ::navigateToHighlightsList
        )

        BottomAppBarItem.menu -> Pair(
            menuRoute,
            ::navigateToMenu
        )
    }
    val navOptions = navOptions {
        launchSingleTop = true
        popUpTo(route)
    }
    navigate(navOptions)
}