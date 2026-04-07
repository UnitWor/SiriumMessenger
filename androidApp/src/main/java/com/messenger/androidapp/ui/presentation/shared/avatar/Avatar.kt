package com.messenger.androidapp.ui.presentation.shared.avatar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.theme.siriumColors

@Preview
@Composable
private fun PrevAvatar() {
    Avatar()
}

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    sizeStatus: Dp = 13.dp,
    endPaddingStatus: Dp = 3.dp,
    bottomPaddingStatus: Dp = 3.dp,
    iconPadding: Dp = 16.dp,
    sizeIcon: Dp = 32.dp,
    onlineStatus: Boolean = false,
    notDisturbStatus: Boolean = false,
    inactiveStatus: Boolean = false,
) {
    Box(
        modifier = modifier
            .background(
                color = siriumColors.textSecondary,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_profile),
            contentDescription = null,
            tint = siriumColors.material.background,
            modifier = Modifier
                .padding(iconPadding)
                .size(sizeIcon)
        )
        Box(
            modifier = Modifier
                .padding(
                    end = endPaddingStatus,
                    bottom = bottomPaddingStatus
                )
                .size(sizeStatus)
                .background(color = siriumColors.green, shape = CircleShape)
                .border(width = 1.dp, color = siriumColors.material.background, shape = CircleShape)
                .align(Alignment.BottomEnd)
        )
    }
}