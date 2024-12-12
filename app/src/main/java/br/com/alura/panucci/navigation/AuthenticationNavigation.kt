package br.com.alura.panucci.navigation

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.ui.screens.AuthenticationScreen
import dataStore
import kotlinx.coroutines.launch

private const val authenticationRoute = "authentication"

fun NavGraphBuilder.authenticationScreen(navController: NavHostController){
    composable(authenticationRoute) {
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        AuthenticationScreen(
            onEnterClick = { user ->
                val userPreferences = stringPreferencesKey("usuario_logado")
                scope.launch {
                    context.dataStore.edit {
                        it[userPreferences] = user
                    }
                }
                navController.navigateToHighlightsList()
            }
        )
    }
}

fun NavController.navigateToAuthentication(navOptions: NavOptions? = null){
    navigate(authenticationRoute, navOptions)
}