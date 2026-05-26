package com.messenger.androidapp.ui.presentation.shared.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.messenger.androidapp.ui.theme.siriumColors

@Composable
fun SiriumIcon(
    modifier: Modifier = Modifier,
    icon: Int,
    tint: Color = Color.Unspecified,
    sizeIcon: Dp = 48.dp,
    paddingTop: Dp = 24.dp,
    paddingStart: Dp = 24.dp,
    paddingEnd: Dp = 24.dp,
    backColor: Color = siriumColors.textSecondary,
    paddingBottom: Dp = 24.dp,
) {
    Box(
        modifier = modifier
            .background(
                color = backColor,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .padding(start = paddingStart, end = paddingEnd, top = paddingTop, bottom =paddingBottom)
                .size(sizeIcon)
        )
    }
}