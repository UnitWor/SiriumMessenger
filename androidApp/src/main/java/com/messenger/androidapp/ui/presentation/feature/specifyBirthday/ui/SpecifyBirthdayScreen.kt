package com.messenger.androidapp.ui.presentation.feature.specifyBirthday.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.feature.customize.ui.Actions
import com.messenger.androidapp.ui.presentation.feature.secureCode.ui.TitleAndSubTitle
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography

private interface SpecifyBirthdayScreen {
    fun onSkipBirthday() {}
}

@Preview
@Composable
private fun PreviewSpecifyBirthday() {
    SpecifyBirthday(
        openCustomizeScreen = {},
        onSkipBirthday = {}
    )
}

@Composable
fun SpecifyBirthdayScreen() {

}

@Composable
fun SpecifyBirthday(
    modifier: Modifier = Modifier,
    openCustomizeScreen: () -> Unit,
    onSkipBirthday: () -> Unit,
) {
    var date by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Content(
            modifier = Modifier
                .align(Alignment.Center),
            date = date,
            onDateChanged = {raw ->
                val filtered = raw.filter { it.isDigit() }.take(8)
                if (filtered != date) {
                    date = filtered
                }
            },
            focusRequester = focusRequester
        )
        Actions(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 27.5.dp),
            textFirstBtn = R.string.btn_next,
            textSecondBtn = R.string.btn_skip,
            onCLickFirstBtn = openCustomizeScreen,
            onCLickSecondBtn = onSkipBirthday
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    date: String,
    onDateChanged: (String) -> Unit,
    focusRequester: FocusRequester
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleAndSubTitle(
            title = R.string.title_birthday,
            subtitle = R.string.subtitle_birthday,
            icon = R.drawable.ic_gift
        )
        BasicTextField(
            value = date,
            onValueChange = onDateChanged,
            modifier = Modifier
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            textStyle = TextStyle(color = Color.Transparent, fontSize = 1.sp),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(2) { i ->
                        DigitCell(
                            digit = date.getOrNull(i),
                            isActive = (i == date.length),
                            onClick = { focusRequester.requestFocus() }
                        )
                    }

                    DotSeparator()

                    repeat(2) { i ->
                        DigitCell(
                            digit = date.getOrNull(i + 2),
                            isActive = (i + 2 == date.length),
                            onClick = { focusRequester.requestFocus() }
                        )
                    }

                    DotSeparator()

                    repeat(4) { i ->
                        DigitCell(
                            digit = date.getOrNull(i + 4),
                            isActive = (i + 4 == date.length),
                            onClick = { focusRequester.requestFocus() }
                        )
                    }
                }
                Box(Modifier.size(0.dp)) {
                    innerTextField()
                }
            }
        )


    }
}

@Composable
private fun DigitCell(
    digit: Char?,
    isActive: Boolean,
    onClick: () -> Unit
) {
    val background by animateColorAsState(
        targetValue = if (isActive) Color(0xFFE0E7FF) else Color(0xFFF3F4F6),
        animationSpec = tween(300),
    )

    Box(
        modifier = Modifier
            .size(31.dp, 43.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(background)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = digit?.toString() ?: "",
            color = siriumColors.textSecondary,
            style = siriumTypography.material.titleLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun DotSeparator() {
    Text(
        text = ".",
        color = siriumColors.material.onSecondary,
        style = siriumTypography.large
    )
}