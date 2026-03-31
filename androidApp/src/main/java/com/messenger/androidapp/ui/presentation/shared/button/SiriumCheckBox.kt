package com.messenger.androidapp.ui.presentation.shared.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    shape: Dp,
    sizeIcon: Dp = 10.dp,
    padding: Dp = 3.dp
) {
    Box(
        modifier = modifier
            .background(color = if (isSelected) siriumColors.material.primary else Color.Unspecified, shape = RoundedCornerShape(shape))
//            .border(
//                color = if (isSelected) Color.Unspecified else ,
//                width = 1.5.dp,
//                shape = RoundedCornerShape(shape)
//            )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_checkmark),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(padding)
                .size(sizeIcon)
        )
    }
}