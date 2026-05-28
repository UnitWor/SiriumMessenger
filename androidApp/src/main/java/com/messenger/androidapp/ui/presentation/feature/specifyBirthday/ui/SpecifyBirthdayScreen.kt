package com.messenger.androidapp.ui.presentation.feature.specifyBirthday.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.approutes.AppRoutes
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
        rememberNavController(),
        siriumColors.material.onPrimary
    )
}

@Composable
fun SpecifyBirthdayScreen(
    navController: NavHostController,
    contentColor: Color
) {
    SpecifyBirthday(
        navController = navController,
        contentColor = contentColor
    )
}

@Composable
fun SpecifyBirthday(
    navController: NavHostController,
    contentColor: Color
) {
    var date by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(contentColor),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Content(
            modifier = Modifier
                .padding(vertical = 141.5.dp),
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
                .padding(horizontal = 24.dp, vertical = 27.5.dp),
            textFirstBtn = R.string.btn_next,
            textSecondBtn = R.string.btn_skip,
            onCLickFirstBtn = {navController.navigate(AppRoutes.CUSTOMIZE)},
            onCLickSecondBtn = {navController.navigate(AppRoutes.CUSTOMIZE)}
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
            icon = R.drawable.ic_gift,
            visibleIcon = true,
            modifierSubtitle = Modifier.padding(horizontal = 47.dp)
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
                        )
                    }

                    DotSeparator()

                    repeat(2) { i ->
                        DigitCell(
                            digit = date.getOrNull(i + 2),
                            isActive = (i + 2 == date.length),
                        )
                    }

                    DotSeparator()

                    repeat(4) { i ->
                        DigitCell(
                            digit = date.getOrNull(i + 4),
                            isActive = (i + 4 == date.length),
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
) {
    val background by animateColorAsState(
        targetValue = if (isActive) siriumColors.backSecondary3 else Color(0xFFF7F9FA),
        animationSpec = tween(300),
    )

    Box(
        modifier = Modifier
            .size(31.dp, 43.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(background),
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