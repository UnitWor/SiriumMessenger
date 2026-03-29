package com.messenger.androidapp.ui.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.messenger.androidapp.ui.presentation.approutes.AppRoutes

@Composable
fun NavigationBuilder(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    visibleBottomBar: Boolean,
    startDestination: String
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = startDestination,
        ){
            composable(AppRoutes.AUTH_REGISTER){

            }
            composable(AppRoutes.CUSTOMIZE_RECOMMENDATION){

            }
            composable(AppRoutes.CUSTOMIZE){

            }
            composable(AppRoutes.SECURE_CODE){

            }
            composable(AppRoutes.FILL_OUT_FORM){

            }
            composable(AppRoutes.SPECIFY_BIRTHDAY){

            }
        }
        if (visibleBottomBar){

        }
    }
}