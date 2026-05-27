package com.messenger.androidapp.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.messenger.androidapp.ui.presentation.approutes.AppRoutes
import com.messenger.androidapp.ui.presentation.navigation.NavigationBuilder
import com.messenger.androidapp.ui.theme.siriumColors

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val routes = listOf(
        AppRoutes.MESSAGE,
        AppRoutes.PROFILE,
        AppRoutes.SEARCH,
        AppRoutes.SETTING,
        AppRoutes.OTHER
    )

    val current = navController
        .currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route

    val visibleBottomBar = current in routes

    ScaffoldHost(
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        NavigationBuilder(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            visibleBottomBar = visibleBottomBar
        )
    }
}

@Composable
fun ScaffoldHost(
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = {},
        content = content
    )
}