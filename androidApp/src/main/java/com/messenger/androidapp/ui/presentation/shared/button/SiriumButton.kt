package com.messenger.androidapp.ui.presentation.shared.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.typography.SiriumTypography

@Preview
@Composable
private fun PrevButtons() {
    Column(
        modifier = Modifier.background(color = Color.LightGray),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SiriumBtn(
            textBtn = "Привет",
            onClick = {},
        )
        SiriumBtn(
            textBtn = "Привет",
            onClick = {},
            isFilledBtn = true
        )
        SiriumBtn(
            textBtn = "Привет",
            onClick = {},
            isGrayBtn = true
        )
        SiriumBtn(
            textBtn = "Привет",
            onClick = {},
            isBlackBtn = true
        )
        SiriumBtn(
            textBtn = "Привет",
            onClick = {},
            bigBtn = false
        )
        SiriumBtn(
            textBtn = "Привет",
            onClick = {},
            bigBtn = false,
            isFilledBtn = true
        )
        SiriumBtn(
            textBtn = "Привет",
            onClick = {},
            bigBtn = false,
            isGrayBtn = true
        )
        SiriumBtn(
            textBtn = "Привет",
            onClick = {},
            bigBtn = false,
            isBlackBtn = true
        )
    }
}

@Composable
fun SiriumBtn(
    modifier: Modifier = Modifier,
    textBtn: String,
    onClick: () -> Unit,
    icon: Int? = null,
    enable: Boolean = true,
    bigBtn: Boolean = true,
    isFilledBtn: Boolean = false,
    isGrayBtn: Boolean = false,
    isBlackBtn: Boolean = false
) {
    val modifierBtn =
        if(bigBtn) modifier.fillMaxWidth()
        else modifier

    val backgroundColor = when{
        isFilledBtn -> siriumColors.material.primary
        isGrayBtn -> siriumColors.material.surface
        isBlackBtn -> siriumColors.backSecondary4
        else -> siriumColors.material.surface
    }

    val txtColor = when{
        isFilledBtn -> siriumColors.material.background
        isGrayBtn -> siriumColors.textSecondary
        isBlackBtn -> siriumColors.material.onPrimary
        else -> siriumColors.material.primary
    }
    val backColor by animateColorAsState(
        targetValue = backgroundColor,
        animationSpec = tween(700)
    )
    val textColor by animateColorAsState(
        targetValue = txtColor,
        animationSpec = tween(700)
    )

    BtnContent(
        modifier = modifierBtn,
        textBtn = textBtn,
        onClick = onClick,
        icon = icon,
        backColor = backColor,
        enable = enable,
        verticalPadding = if (bigBtn) 11.5.dp else 9.dp,
        horizontalPadding = if (bigBtn) 0.dp else 15.5.dp,
        textColor = textColor,
        textStyle = if (bigBtn)SiriumTypography.material.labelMedium else SiriumTypography.extraSmallMedium
    )
}

@Composable
fun BtnContent(
    modifier: Modifier = Modifier,
    textBtn: String,
    backColor: Color,
    enable: Boolean,
    onClick: () -> Unit,
    verticalPadding: Dp,
    horizontalPadding: Dp,
    textColor: Color,
    textStyle: TextStyle,
    icon: Int?
) {
    Box(
        modifier = modifier
            .background(color = backColor, shape = RoundedCornerShape(16.dp))
            .clickable(enabled = enable, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = verticalPadding, horizontal = horizontalPadding),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    imageVector = ImageVector.vectorResource(it),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            Text(
                text = textBtn,
                color = textColor,
                style = textStyle
            )
        }
    }
}