package com.messenger.androidapp.ui.presentation.feature.customize.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.approutes.AppRoutes
import com.messenger.androidapp.ui.presentation.feature.secureCode.ui.TitleAndSubTitle
import com.messenger.androidapp.ui.presentation.shared.button.SiriumBtn
import com.messenger.androidapp.ui.presentation.shared.icon.SiriumIcon
import com.messenger.androidapp.ui.theme.siriumColors

@Preview
@Composable
private fun PreviewCustomize() {
    Content(
        rememberNavController(),
        siriumColors.material.onPrimary
    )
}

@Composable
fun CustomizeScreen(
    navController: NavHostController,
    contentColor: Color
) {
    Content(
        navController = navController,
        contentColor = contentColor
    )
}

@Composable
private fun Content(
    navController: NavHostController,
    contentColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(contentColor),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TitleAndSubTitle(
            modifier = Modifier.padding(vertical = 200.dp, horizontal = 49.dp),
            title = R.string.title_customize,
            subtitle = R.string.subtitle_customize,
            verticalPadding = 8.dp,
            icon = R.drawable.ic_emoji_edit,
            visibleIcon = true,
        )
        Actions(
          modifier = Modifier
              .padding(vertical = 7.5.dp, horizontal = 24.dp),
            textFirstBtn = R.string.btn_settings,
            textSecondBtn = R.string.btn_skip,
            onCLickFirstBtn = {navController.navigate(AppRoutes.CUSTOMIZE_RECOMMENDATION)},
            onCLickSecondBtn = {navController.navigate(AppRoutes.OTHER)}
        )
    }
}

@Composable
fun Actions(
    modifier: Modifier = Modifier,
    textFirstBtn: Int,
    textSecondBtn: Int,
    enable: Boolean = true,
    onCLickFirstBtn: () -> Unit,
    onCLickSecondBtn: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SiriumBtn(
            textBtn = stringResource(textFirstBtn),
            onClick = onCLickFirstBtn,
            isFilledBtn = true
        )
        SiriumBtn(
            textBtn = stringResource(textSecondBtn),
            onClick = onCLickSecondBtn,
            enable = enable
        )
    }
}