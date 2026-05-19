package com.messenger.androidapp.ui.presentation.shared.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.theme.siriumColors

@Composable
fun SiriumCheckBox(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    shape: Dp,
    sizeIcon: Dp = 10.dp,
    padding: Dp = 3.dp
) {
    val backColor by animateColorAsState(
        targetValue = if (isSelected) siriumColors.material.primary else Color.Unspecified,
        animationSpec = tween(300)
    )
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) Color.Unspecified else siriumColors.sky2,
        animationSpec = tween(300)
    )
    val iconColor by animateColorAsState(
        targetValue = if (isSelected) Color.Unspecified else Color.Transparent,
        animationSpec = tween(300)
    )

    Box(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .background(color = backColor, shape = RoundedCornerShape(shape))
            .border(
                color = borderColor,
                width = 1.5.dp,
                shape = RoundedCornerShape(shape)
            )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_checkmark),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier
                .padding(padding)
                .size(sizeIcon)
        )
    }
}