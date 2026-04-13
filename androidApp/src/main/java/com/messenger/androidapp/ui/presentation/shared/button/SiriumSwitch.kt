package com.messenger.androidapp.ui.presentation.shared.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.theme.siriumColors

@Preview
@Composable
private fun PrevSiriumSwitch() {
    var isSelected by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.background(siriumColors.material.background),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SiriumSwitch(
            onClick = {isSelected = !isSelected},
            isSelected = isSelected,
            activeSwitch = isSelected,
            unActiveSwitch = !isSelected
        )
        SiriumSwitch(
            onClick = {},
            enabled = false,
            outlineActive = true
        )
        SiriumSwitch(
            onClick = {},
            enabled = false,
        )
    }
}

@Composable
fun SiriumSwitch(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit,
    activeSwitch: Boolean = false,
    unActiveSwitch: Boolean = false,
    outlineActive: Boolean = false,
    enabled: Boolean = true,
) {

    val backColor = when{
        activeSwitch -> siriumColors.material.primary
        unActiveSwitch -> siriumColors.material.surface
        outlineActive -> siriumColors.sky2
        else -> siriumColors.material.background
    }

    val circleColor = when{
        activeSwitch -> siriumColors.material.background
        unActiveSwitch -> siriumColors.material.background
        outlineActive -> siriumColors.material.surface
        else -> siriumColors.sky2
    }

    val switchColor by animateColorAsState(
        targetValue = backColor,
        animationSpec = tween(700)
    )

    val circleColors by animateColorAsState(
        targetValue = circleColor,
        animationSpec = tween(700)
    )

    val rightPadding by animateDpAsState(
        targetValue = if (isSelected) 2.dp else 26.dp,
        animationSpec = tween(700)
    )
    val leftPadding by animateDpAsState(
        targetValue = if (isSelected) 26.dp else 2.dp,
        animationSpec = tween(700)    )

    Box(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                enabled = enabled,
                onClick = onClick
            )
            .border(
                1.dp,
                if (unActiveSwitch || activeSwitch || outlineActive) Color.Unspecified
                else siriumColors.material.outline,
                CircleShape
            )
            .background(switchColor, CircleShape)
    ) {
        Box(
            modifier = Modifier
                .padding(
                    start = leftPadding,
                    end = rightPadding,
                    top = 2.dp,
                    bottom = 2.dp
                )
                .size(28.dp)
                .background(circleColors, CircleShape)
        )
    }
}