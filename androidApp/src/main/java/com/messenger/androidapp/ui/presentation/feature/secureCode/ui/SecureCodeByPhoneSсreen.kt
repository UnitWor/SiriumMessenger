package com.messenger.androidapp.ui.presentation.feature.secureCode.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography

@Composable
fun SecureCodeByPhoneScreen(modifier: Modifier = Modifier) {

}

@Composable
private fun Content(){
    Column() {

    }
}


@Composable
fun CodeBlock(
    modifier: Modifier = Modifier,
    phone: Int
) {
    val horizontalPadding = when(phone){
        1 -> 24.5.dp
        2, 3, 5 -> 22.dp
        4, 0 -> 20.5.dp
        6, 7, 9 -> 21.5.dp
        8 -> 21.dp
        else -> 0.dp
    }

    Box(
        modifier = modifier
    ) {
        Text(
            text = phone.toString(),
            color = siriumColors.textSecondary,
            style = siriumTypography.material.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = horizontalPadding)
        )
    }
}