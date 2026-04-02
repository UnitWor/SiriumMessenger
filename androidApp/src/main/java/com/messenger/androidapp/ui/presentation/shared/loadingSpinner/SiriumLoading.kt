package com.messenger.androidapp.ui.presentation.shared.loadingSpinner

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.messenger.androidapp.ui.theme.SiriumTheme
import com.messenger.androidapp.ui.theme.siriumColors

@Preview
@Composable
private fun PreviewSiriumLoadingSpinner() {
    SiriumTheme() {
        SiriumLoadingSpinner()
    }
}

@Composable
fun SiriumLoadingSpinner(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = siriumColors.material.primary,
        strokeWidth = 5.dp
    )
}