package com.messenger.androidapp.ui.presentation.feature.settings.ui.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.feature.settings.ui.AccordionNavigation
import com.messenger.androidapp.ui.presentation.feature.settings.ui.SettingWithRadioBtn
import com.messenger.androidapp.ui.presentation.feature.settings.ui.Settings
import com.messenger.androidapp.ui.presentation.shared.button.SiriumBtn
import com.messenger.androidapp.ui.theme.siriumColors

data class NotificationsAndSound(
    val setting: Int,
    val onClick: () -> Unit,
    val isSelected: Boolean
)

@Preview
@Composable
private fun PreviewNotificationAndSound() {
    Content(
        rememberNavController(),
        siriumColors.material.onPrimary
    )
}

@Composable
fun NotificationAndSoundScreen(
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
    var sound by remember { mutableStateOf(true) }
    var vibration by remember { mutableStateOf(false) }
    var channels by remember { mutableStateOf(true) }
    var countMessage by remember { mutableStateOf(true) }
    var showText by remember { mutableStateOf(true) }

    val notificationMessages = listOf(
        Settings(
            icon = R.drawable.ic_profile,
            setting = R.string.personal_chats,
            onClick = {}
        ),
        Settings(
            icon = R.drawable.ic_groups,
            setting = R.string.groups,
            onClick = {}
        ),
        Settings(
            icon = R.drawable.ic_channel,
            setting = R.string.channels,
            onClick = {}
        ),
    )

    val notificationsApp = listOf(
        NotificationsAndSound(
            setting = R.string.sound,
            onClick = { sound = !sound },
            isSelected = sound
        ),
        NotificationsAndSound(
            setting = R.string.vibration,
            onClick = { vibration = !vibration },
            isSelected = vibration
        ),
        NotificationsAndSound(
            setting = R.string.show_text,
            onClick = { showText = !showText },
            isSelected = showText
        ),
    )

    val tagOnTheIcon = listOf(
        NotificationsAndSound(
            setting = R.string.channels,
            onClick = { channels = !channels },
            isSelected = channels
        ),
        NotificationsAndSound(
            setting = R.string.count_message,
            onClick = { countMessage = !countMessage },
            isSelected = countMessage
        ),
    )

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(contentColor)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        SiriumBtn(
            textBtn = stringResource(R.string.btn_back),
            onClick = {navController.popBackStack()},
            bigBtn = false
        )
        Settings(
            label = stringResource(R.string.message_notification),
            content = {
                notificationMessages.forEach { notificationMessage ->
                    AccordionNavigation(
                        nameSettings = notificationMessage.setting,
                        icon = notificationMessage.icon,
                        onClick = notificationMessage.onClick
                    )
                }
            }
        )
        Settings(
            label = stringResource(R.string.app_notification),
            content = {
                notificationsApp.forEach { notificationsApp ->
                    SettingWithRadioBtn(
                        nameSettings = notificationsApp.setting,
                        isSelected = notificationsApp.isSelected,
                        onClick = notificationsApp.onClick
                    )
                }
            }
        )
        Settings(
            label = stringResource(R.string.the_tag_on_the_icon),
            content = {
                tagOnTheIcon.forEach { item ->
                    SettingWithRadioBtn(
                        nameSettings = item.setting,
                        isSelected = item.isSelected,
                        onClick = item.onClick
                    )
                }
            }
        )
        SiriumBtn(
            textBtn = stringResource(R.string.reset_settings),
            onClick = {}
        )
    }
}