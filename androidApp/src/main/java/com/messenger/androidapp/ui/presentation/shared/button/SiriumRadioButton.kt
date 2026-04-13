package com.messenger.androidapp.ui.presentation.shared.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.messenger.androidapp.ui.theme.color.LightSiriumColor
import com.messenger.androidapp.ui.theme.siriumColors

@Preview
@Composable
private fun PrevSiriumRadioButton() {
    var isSelected by remember { mutableStateOf(false) }

    SiriumRadioButton(
        onClick = {isSelected = !isSelected},
        isSelectedRadio = isSelected
    )
}

@Composable
fun SiriumRadioButton(
    modifier: Modifier = Modifier,
    insideBoxPadding: Dp = 10.dp,
    insideBoxSize: Dp = 12.dp,
    onClick: () -> Unit,
    isSelectedRadio: Boolean = false
) {
    val backColor by animateColorAsState(
        targetValue =
            if (isSelectedRadio) siriumColors.material.primary
            else Color.Unspecified,
        animationSpec = tween(300)
    )

    val borderColor by animateColorAsState(
        targetValue =
            if (isSelectedRadio) Color.Unspecified
            else siriumColors.sky2,
        animationSpec = tween(300)
    )

    val insideColorBox by animateColorAsState(
        targetValue =
            if (isSelectedRadio) siriumColors.material.background
            else Color.Unspecified,
        animationSpec = tween(300)
    )

    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .border(2.dp, borderColor, CircleShape)
            .background(backColor, CircleShape)
    ) {
        Box(
            modifier = Modifier
                .padding(insideBoxPadding)
                .size(insideBoxSize)
                .background(insideColorBox, CircleShape)
        )
    }
}