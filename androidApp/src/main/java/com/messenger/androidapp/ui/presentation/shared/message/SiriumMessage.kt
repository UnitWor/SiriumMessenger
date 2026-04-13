package com.messenger.androidapp.ui.presentation.shared.message

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography

@Composable
fun SiriumMessage(
    modifier: Modifier = Modifier,
    textColor: Color = siriumColors.material.onSecondary,
    message: String,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = message,
            color = textColor,
            style = siriumTypography.material.labelMedium,
            modifier = Modifier.fillMaxWidth()
        )
//        Icon(
//            imageVector = ImageVector.vectorResource(),
//            contentDescription = null
//        )
    }
}