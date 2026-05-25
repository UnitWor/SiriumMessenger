package com.messenger.androidapp.ui.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.messenger.androidapp.ui.presentation.approutes.AppRoutes
import com.messenger.androidapp.ui.presentation.feature.authRegister.ui.AuthAndRegisterScreen
import com.messenger.androidapp.ui.presentation.feature.customize.ui.CustomizeScreen
import com.messenger.androidapp.ui.presentation.feature.fillOutForm.ui.FillOutForm
import com.messenger.androidapp.ui.presentation.feature.fillOutForm.ui.FillOutFormScreen
import com.messenger.androidapp.ui.presentation.feature.secureCode.ui.SecureCodeByPhoneScreen
import com.messenger.androidapp.ui.presentation.navigation.bottomBar.SiriumBottomBar
import io.github.fletchmckee.liquid.liquefiable
import io.github.fletchmckee.liquid.rememberLiquidState

@Composable
fun NavigationBuilder(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    visibleBottomBar: Boolean,
) {
    val liquidState = rememberLiquidState()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .liquefiable(liquidState),
            navController = navController,
            startDestination = AppRoutes.AUTH_REGISTER,
        ){
            composable(AppRoutes.AUTH_REGISTER){
                AuthAndRegisterScreen()
            }
            composable(AppRoutes.CUSTOMIZE_RECOMMENDATION){

            }
            composable(AppRoutes.CUSTOMIZE){
                CustomizeScreen()
            }
            composable(AppRoutes.SECURE_CODE){
                SecureCodeByPhoneScreen()
            }
            composable(AppRoutes.FILL_OUT_FORM){
                FillOutFormScreen()
            }
            composable(AppRoutes.SPECIFY_BIRTHDAY){

            }
            composable(AppRoutes.PROFILE){

            }
            composable(AppRoutes.SETTING){

            }
            composable(AppRoutes.SEARCH){

            }
            composable(AppRoutes.MESSAGE){

            }
        }
        if (visibleBottomBar){
            SiriumBottomBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                liquidState = liquidState,
                navController = navController
            )
        }
    }
}