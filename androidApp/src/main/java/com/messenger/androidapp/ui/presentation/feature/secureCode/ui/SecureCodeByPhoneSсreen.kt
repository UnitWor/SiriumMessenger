package com.messenger.androidapp.ui.presentation.feature.secureCode.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.shared.button.SiriumBtn
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography
import com.messenger.androidapp.ui.theme.typography.montserrat
import kotlinx.coroutines.delay

private interface SecureCodeByPhoneSсreen {

}
@Preview
@Composable
fun SecureCodeByPhoneScreen() {
    SecureCodeByPhone()
}

@Composable
private fun SecureCodeByPhone() {

    var isError by remember { mutableStateOf(false) }
    var code by remember { mutableStateOf("") }
    var remainingSeconds by remember { mutableIntStateOf(0) }
    var timerKey by remember { mutableIntStateOf(0) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(timerKey) {
        remainingSeconds = 60
        while (remainingSeconds > 0) {
            delay(1000L)
            remainingSeconds--
        }
    }

    val isCooldownActive = remainingSeconds > 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Content(
            content = {
                TitleAndSubTitle()
                BasicTextField(
                    value = code,
                    onValueChange = { newValue ->
                        if (newValue.length <= 4 && newValue.all { it.isDigit() }) {
                            isError = false
                            code = newValue
                        }
                    },
                    modifier = Modifier
                        .focusRequester(focusRequester),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    textStyle = TextStyle(color = Color.Transparent, fontSize = 1.sp),
                    cursorBrush = SolidColor(Color.Transparent),
                    decorationBox = { innerTextField ->

                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(4) { index ->
                                CodeCell(
                                    digit = code.getOrNull(index)?.toString() ?: "",
                                    isActive = !isError && index == code.length,
                                    isError = isError
                                )
                            }
                        }
                        Box(Modifier.size(0.dp)) {
                            innerTextField()
                        }
                    }
                )
            }
        )
        ActionAndTime(
            openFillOutForm = {},
            requestOnAgainCode = {
                code = ""
                isError = false
                timerKey++
            },
            isError = isError,
            isCooldownActive = isCooldownActive,
            remainingSeconds = remainingSeconds
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .padding(vertical = 75.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        content()
    }
}

@Composable
fun TitleAndSubTitle(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.enter_code_title),
            color = siriumColors.material.onSecondary,
            style = siriumTypography.material.headlineSmall,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.enter_code_subtitle),
            color = siriumColors.textSecondary,
            style = siriumTypography.material.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun CodeCell(
    modifier: Modifier = Modifier,
    digit: String,
    isActive: Boolean = false,
    isError: Boolean = false
) {
    val background = when {
        isActive -> siriumColors.backSecondary3
        isError -> siriumColors.material.error
        else -> siriumColors.material.background
    }

    val back by animateColorAsState(
        targetValue = background,
        animationSpec = tween(400)
    )

    Box(
        modifier = modifier
            .size(58.dp, 64.dp)
            .background(color = back, shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        if (digit.isNotEmpty()) {
            Text(
                text = digit,
                style = TextStyle(
                    color = siriumColors.textSecondary,
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    fontFamily = montserrat,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Composable
fun ActionAndTime(
    modifier: Modifier = Modifier,
    openFillOutForm: () -> Unit,
    requestOnAgainCode: () -> Unit,
    isError: Boolean,
    isCooldownActive: Boolean,
    remainingSeconds: Int
) {
    val minutes = remainingSeconds / 60
    val seconds = remainingSeconds % 60
    val timerText = "$minutes:%02d".format(seconds)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 9.5.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = when {
                isCooldownActive -> timerText
                isError -> stringResource(R.string.you_entered_the_wrong_code)
                else -> ""
            },
            color = if (isError) siriumColors.material.error else siriumColors.material.primary,
            style = siriumTypography.material.labelLarge,
            textAlign = TextAlign.Center
        )
        SiriumBtn(
            textBtn = stringResource(R.string.btn_next),
            onClick = openFillOutForm,
            isFilledBtn = true
        )
        SiriumBtn(
            textBtn = stringResource(R.string.enter_the_code_again),
            onClick = requestOnAgainCode,
            enable = !isCooldownActive
        )
    }
}