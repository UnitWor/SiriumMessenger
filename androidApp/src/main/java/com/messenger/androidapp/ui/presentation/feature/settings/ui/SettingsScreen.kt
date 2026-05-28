package com.messenger.androidapp.ui.presentation.feature.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.approutes.AppRoutes
import com.messenger.androidapp.ui.presentation.shared.button.SiriumRadioButton
import com.messenger.androidapp.ui.presentation.shared.button.SiriumSwitch
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography

data class Settings(
    val icon: Int,
    val setting: Int,
    val onClick: () -> Unit
)

@Preview
@Composable
private fun PreviewSettings() {
    Content(
        contentColor = siriumColors.material.onPrimary,
        navController = rememberNavController()
    )
}

@Composable
fun SettingsScreen(
    contentColor: Color,
    navController: NavHostController
) {
    Content(
        contentColor = contentColor,
        navController = navController
    )
}

@Composable
private fun Content(
    contentColor: Color,
    navController: NavHostController
) {
    val settings = listOf(
        Settings(
            icon = R.drawable.ic_notification,
            setting = R.string.notification_and_sound,
            onClick = {navController.navigate(AppRoutes.NOTIFICATIONS_AND_SOUND)}
        ),
        Settings(
            icon = R.drawable.ic_privacy,
            setting = R.string.privacy,
            onClick = {}
        ),
        Settings(
            icon = R.drawable.ic_faq,
            setting = R.string.faq,
            onClick = {}
        ),
        Settings(
            icon = R.drawable.ic_question,
            setting = R.string.question,
            onClick = {}
        ),
        Settings(
            icon = R.drawable.ic_battery,
            setting = R.string.battery,
            onClick = {}
        ),
        Settings(
            icon = R.drawable.ic_decoration,
            setting = R.string.decoration,
            onClick = {}
        ),
        Settings(
            icon = R.drawable.ic_call,
            setting = R.string.call,
            onClick = {}
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(contentColor)
            .padding(24.dp)
    ) {
        Settings(
            label = stringResource(R.string.basic_settings),
            descriptionSettings = stringResource(R.string.description_settings),
            visibleDecorationSettings = true,
            content = {
                settings.forEach { setting ->
                    AccordionNavigation(
                        nameSettings = setting.setting,
                        icon = setting.icon,
                        onClick = setting.onClick
                    )
                }
            }
        )
    }
}

@Composable
fun Settings(
    modifier: Modifier = Modifier,
    label: String,
    visibleDecorationSettings: Boolean = false,
    descriptionSettings: String = "",
    content: @Composable (ColumnScope) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            color = siriumColors.textSecondary,
            style = siriumTypography.extraSmallMedium
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = siriumColors.material.background,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(
                    vertical = 4.dp,
                    horizontal = 7.dp
                )
        ) {
            content.invoke(this)
        }
        if (visibleDecorationSettings) {
            Text(
                text = descriptionSettings,
                color = siriumColors.textSecondary,
                style = siriumTypography.extraSmallMedium
            )
        }
    }
}

@Composable
fun AccordionNavigation(
    modifier: Modifier = Modifier,
    nameSettings: Int,
    icon: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .fillMaxWidth()
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(icon),
                    contentDescription = null,
                    tint = siriumColors.material.primary
                )
                Text(
                    text = stringResource(nameSettings),
                    color = siriumColors.material.onSecondary,
                    style = siriumTypography.material.labelMedium
                )
            }
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_right),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = siriumColors.sky4
        )
    }
}

@Composable
fun SettingWithRadioBtn(
    modifier: Modifier = Modifier,
    nameSettings: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(nameSettings),
                color = siriumColors.material.onSecondary,
                style = siriumTypography.material.labelMedium
            )
            SiriumSwitch(
                isSelected = isSelected,
                activeSwitch = isSelected,
                unActiveSwitch = !isSelected,
                onClick = onClick
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = siriumColors.sky4
        )
    }
}