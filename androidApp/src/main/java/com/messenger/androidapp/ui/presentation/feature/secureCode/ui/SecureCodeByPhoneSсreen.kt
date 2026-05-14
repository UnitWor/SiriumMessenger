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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.messenger.androidapp.ui.theme.SiriumTheme
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.typography.montserrat
import kotlinx.coroutines.delay

@Composable
fun SecureCodeByPhoneScreen(modifier: Modifier = Modifier) {

}

@Preview
@Composable
private fun SecureCodeByPhone() {
    Column() {
        Content() { }
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    onCodeComplete: (String) -> Unit,
) {
    var code by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    val focusRequesters = remember { List(4) { FocusRequester() } }

    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }

    // Следим за длиной кода для переключения фокуса
    LaunchedEffect(code.length) {
        when {
            code.length < 4 && !isError -> {
                focusRequesters[code.length].requestFocus()
            }
            code.length == 4 -> {
                if (code == "1234") {
                    onCodeComplete(code)
                } else {
                    isError = true
                    delay(1000)
                    code = ""
                    isError = false
                    focusRequesters[0].requestFocus()
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleAndSubTitle()
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 0 until 4) {
                val digit = code.getOrNull(i)?.toString()
                val isActive = !isError && i == code.length
                CodeBlock(
                    code = digit ?: "",
                    activeCode = isActive,
                    errorCode = isError,
                    focusRequesters = focusRequesters[i],
                    onCodeChange = { newValue ->
                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                            val newCode = when {
                                newValue.isEmpty() -> {
                                    if (i < code.length) {
                                        code.removeRange(i, i + 1)
                                    } else {
                                        code
                                    }
                                }
                                i == code.length -> code + newValue
                                i < code.length -> {
                                    code.substring(0, i) + newValue + code.substring(i + 1)
                                }
                                else -> code
                            }
                            code = newCode
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun TitleAndSubTitle(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
//        Text(
//            text = ,
//            color = ,
//            style = ,
//        )
    }
}

@Composable
fun CodeBlock(
    modifier: Modifier = Modifier,
    code: String,
    onCodeChange: (String) -> Unit,
    activeCode: Boolean = false,
    focusRequesters: FocusRequester,
    errorCode: Boolean = false
) {
    val background = when {
        activeCode -> siriumColors.backSecondary3
        errorCode -> siriumColors.material.error
        else -> siriumColors.material.background
    }

    val back by animateColorAsState(
        targetValue = background,
        animationSpec = tween(400)
    )

    val textStyle = TextStyle(
        color = siriumColors.textSecondary,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium
    )

    Box(
        modifier = modifier
            .size(58.dp, 64.dp)
            .background(color = back, shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            modifier = Modifier
                .padding(horizontal = 20.5.dp)
                .focusRequester(focusRequesters),
            value = code,
            onValueChange = onCodeChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            cursorBrush = SolidColor(siriumColors.material.primary),
            textStyle = textStyle,
        )
    }
}