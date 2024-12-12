package br.com.alura.panucci.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.ui.screens.HighlightsListScreen
import br.com.alura.panucci.ui.viewmodels.HighlightsListViewModel
import dataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlin.random.Random

internal const val highlistsListRoute = "highlight"
fun NavGraphBuilder.highLightsListScreen(navController: NavHostController){
    composable(highlistsListRoute) {
        val viewModel: HighlightsListViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()
        val userPreferences = stringPreferencesKey("usuario_logado")
        val context = LocalContext.current
        var user: String? by remember {
            mutableStateOf(null)
        }
        var dataState by remember {
            mutableStateOf("loading")
        }
        LaunchedEffect(null) {
            val randomMillis = Random.nextLong(500, 1000)
            delay(randomMillis)
            user = context.dataStore.data.first()[userPreferences]
            dataState = "finished"
        }
        when (dataState) {
            "loading" -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Carregando...",
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }

            "finished" -> {
                user?.let {
                    HighlightsListScreen(
                        uiState = uiState,
                        onNavigateToDetails = { product ->
                            navController.navigateToProductDetails(product.id)
                        },
                        onNavigateToCheckout = {
                            navController.navigateToCheckout()
                        },
                    )
                } ?: LaunchedEffect(null) {
                    navController.navigateToAuthentication()
                }
            }
        }
    }
}

fun NavController.navigateToHighlightsList(navOptions: NavOptions? = null){
    navigate(highlistsListRoute, navOptions)
}