package com.messenger.androidapp.ui.presentation.shared.screens.success

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.approutes.AppRoutes
import com.messenger.androidapp.ui.presentation.shared.button.SiriumBtn
import com.messenger.androidapp.ui.presentation.shared.icon.SiriumIcon
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography

@Composable
fun RegisterSuccessScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Content(
        modifier = modifier,
        openMainScreen = {navController.navigate(AppRoutes.OTHER)}
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    openMainScreen: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SiriumIcon(
                icon = R.drawable.ic_checkmark,
            )
            Text(
                text = stringResource(R.string.everything_is_ready),
                color = siriumColors.material.onSecondary,
                style = siriumTypography.material.headlineSmall
            )
        }
        SiriumBtn(
            textBtn = stringResource(R.string.btn_next),
            onClick = openMainScreen,
            isFilledBtn = true,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 11.5.dp, horizontal = 24.dp)
        )
    }
}
