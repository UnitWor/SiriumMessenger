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
import androidx.compose.ui.unit.dp
import com.messenger.androidapp.ui.theme.siriumColors

@Composable
fun SiriumIcon(
    modifier: Modifier = Modifier,
    icon: Int
) {
    Box(
        modifier = modifier
            .background(
                color = siriumColors.material.primary,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(24.dp)
                .size(48.dp)
        )
    }
}