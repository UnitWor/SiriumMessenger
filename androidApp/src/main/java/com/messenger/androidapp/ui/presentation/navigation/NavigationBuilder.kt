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
import com.messenger.androidapp.ui.presentation.feature.customize.ui.recommendations.CustomizeRecommendationScreen
import com.messenger.androidapp.ui.presentation.feature.fillOutForm.ui.FillOutForm
import com.messenger.androidapp.ui.presentation.feature.fillOutForm.ui.FillOutFormScreen
import com.messenger.androidapp.ui.presentation.feature.main.ui.MainScreen
import com.messenger.androidapp.ui.presentation.feature.post.PostScreen
import com.messenger.androidapp.ui.presentation.feature.secureCode.ui.SecureCodeByPhoneScreen
import com.messenger.androidapp.ui.presentation.feature.specifyBirthday.ui.SpecifyBirthday
import com.messenger.androidapp.ui.presentation.feature.specifyBirthday.ui.SpecifyBirthdayScreen
import com.messenger.androidapp.ui.presentation.navigation.bottomBar.SiriumBottomBar
import com.messenger.androidapp.ui.presentation.shared.screens.success.RegisterSuccessScreen
import com.messenger.androidapp.ui.theme.siriumColors
import io.github.fletchmckee.liquid.liquefiable
import io.github.fletchmckee.liquid.rememberLiquidState

@Composable
fun NavigationBuilder(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    visibleBottomBar: Boolean,
) {
    val liquidState = rememberLiquidState()
    val contentColor = siriumColors.material.onPrimary

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
                AuthAndRegisterScreen(
                    navController = navController,
                    contentColor = contentColor
                )
            }
            composable(AppRoutes.CUSTOMIZE_RECOMMENDATION){
                CustomizeRecommendationScreen(
                    navController = navController,
                    contentColor = contentColor
                )
            }
            composable(AppRoutes.CUSTOMIZE){
                CustomizeScreen(
                    navController = navController,
                    contentColor = contentColor
                )
            }
            composable(AppRoutes.SECURE_CODE){
                SecureCodeByPhoneScreen(
                    navController = navController,
                    contentColor = contentColor
                )
            }
            composable(AppRoutes.FILL_OUT_FORM){
                FillOutFormScreen(
                    navController = navController,
                    contentColor = contentColor
                )
            }
            composable(AppRoutes.SPECIFY_BIRTHDAY){
                SpecifyBirthdayScreen(
                    navController = navController,
                    contentColor = contentColor
                )
            }
            composable(AppRoutes.PROFILE){

            }
            composable(AppRoutes.SETTING){

            }
            composable(AppRoutes.SEARCH){

            }
            composable(AppRoutes.MESSAGE){

            }
            composable(AppRoutes.POST){
                PostScreen(
                    contentColor = contentColor
                )
            }
            composable(AppRoutes.OTHER){
                MainScreen(
                    navController = navController,
                    contentColor = contentColor
                )
            }
            composable(AppRoutes.SUCCESS){
                RegisterSuccessScreen(
                    navController = navController,
                    contentColor = contentColor
                )
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