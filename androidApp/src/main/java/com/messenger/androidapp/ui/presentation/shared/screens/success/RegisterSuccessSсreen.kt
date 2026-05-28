package com.messenger.androidapp.ui.presentation.shared.screens.success

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.approutes.AppRoutes
import com.messenger.androidapp.ui.presentation.feature.secureCode.ui.TitleAndSubTitle
import com.messenger.androidapp.ui.presentation.shared.button.SiriumBtn
import com.messenger.androidapp.ui.theme.siriumColors

@Composable
fun RegisterSuccessScreen(
    navController: NavHostController,
    contentColor: Color
) {
    Content(
        openMainScreen = {navController.navigate(AppRoutes.OTHER)},
        contentColor = contentColor
    )
}

@Preview
@Composable
private fun PreviewRegisterSuccess() {
    Content(
        contentColor = siriumColors.material.onPrimary,
        openMainScreen = {}
    )
}

@Composable
private fun Content(
    openMainScreen: () -> Unit,
    contentColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(contentColor),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TitleAndSubTitle(
            modifier = Modifier.padding(vertical = 260.dp),
            title = R.string.everything_is_ready,
            visibleSubtitle = false,
            subtitle = 0,
            verticalPadding = 8.dp,
            visibleIcon = true
        )
        SiriumBtn(
            textBtn = stringResource(R.string.btn_next),
            onClick = openMainScreen,
            isFilledBtn = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.5.dp, horizontal = 24.dp)
        )
    }
}