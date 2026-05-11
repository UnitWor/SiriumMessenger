package com.messenger.androidapp.ui.presentation.feature.authRegister.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.shared.button.SiriumBtn
import com.messenger.androidapp.ui.presentation.shared.segmentControl.SiriumSegmentControl
import com.messenger.androidapp.ui.presentation.shared.text.PasswordTextField
import com.messenger.androidapp.ui.presentation.shared.text.SiriumTextField
import com.messenger.androidapp.ui.theme.SiriumTheme
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography
import kotlinx.coroutines.delay

@Preview
@Composable
fun AuthAndRegisterScreen(
    modifier: Modifier = Modifier
) {
    SiriumTheme {
        AuthAndRegister(
            modifier = modifier
        )
    }
}

@Composable
private fun AuthAndRegister(
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val authAndRegister = listOf(AuthAndRegister.AUTH, AuthAndRegister.REGISTER)
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        LogoAuthAndRegister(
            authAndRegister = authAndRegister,
            selectedIndex = selectedIndex
        )
        Content(
            authAndRegister = authAndRegister,
            selectedIndex = selectedIndex,
            onSelectedIndex = { selectedIndex = it },
            onLogInGmail = {},
            openMainScreen = {},
            openSecureCode = {}
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    authAndRegister: List<String>,
    selectedIndex: Int,
    onLogInGmail: () -> Unit,
    openMainScreen: () -> Unit,
    openSecureCode: () -> Unit,
    onSelectedIndex: (Int) -> Unit
) {
    var numberPhone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = if (authAndRegister[selectedIndex] == AuthAndRegister.AUTH) 9.dp else 39.dp,
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginWithPhone(
            password = password,
            onPasswordChange = { password = it },
            numberPhone = numberPhone,
            onNumberPhoneChange = { numberPhone = it },
            selectedIndex = selectedIndex,
            onSelectedIndex = onSelectedIndex,
            authAndRegister = authAndRegister
        )
        SiriumBtn(
            textBtn = stringResource(
                if (authAndRegister[selectedIndex] == AuthAndRegister.AUTH)R.string.enter
                else R.string.registration
            ),
            onClick = {
                if (authAndRegister[selectedIndex] == AuthAndRegister.AUTH){
                    openMainScreen()
                } else{
                    openSecureCode()
                }
            },
            isFilledBtn = true
        )
        Or()
        SiriumBtn(
            textBtn = stringResource(R.string.log_in_using_gmail),
            icon = R.drawable.ic_gmail,
            isBlackBtn = true,
            onClick = onLogInGmail,
        )
    }
}

@Composable
fun LoginWithPhone(
    modifier: Modifier = Modifier,
    authAndRegister: List<String>,
    selectedIndex: Int,
    onSelectedIndex: (Int) -> Unit,
    password: String,
    numberPhone: String,
    onPasswordChange: (String) -> Unit,
    onNumberPhoneChange: (String) -> Unit
) {
    val headerText = when (authAndRegister[selectedIndex]) {
        AuthAndRegister.AUTH -> stringResource(R.string.log_in_account)
        AuthAndRegister.REGISTER -> stringResource(R.string.create_an_account)
        else -> ""
    }
    var isAnimating by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TypewriterTextWithCursor(
            text = headerText,
            durationMs = 800,
            showCursor = !isAnimating,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        SiriumSegmentControl(
            options = authAndRegister,
            selectedIndex = selectedIndex,
            onSelectedChanged = onSelectedIndex
        )
        SiriumTextField(
            text = numberPhone,
            singleLine = true,
            keybOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            placeholder = stringResource(R.string.number_phone),
            onTextFieldChange = onNumberPhoneChange
        )
        AnimatedVisibility(
            visible = authAndRegister[selectedIndex] == AuthAndRegister.AUTH,
            modifier = Modifier
                .fillMaxWidth(),
            enter = fadeIn(animationSpec = tween(200)) +
                    slideInVertically(
                        initialOffsetY = { -it / 2 },
                        animationSpec = tween(200)
                    ),
            exit = fadeOut(animationSpec = tween(200)) +
                    slideOutVertically(
                        targetOffsetY = { -it / 2 },
                        animationSpec = tween(200)
                    )
        ) {
            PasswordTextField(
                password = password,
                onPasswordChange = onPasswordChange
            )
        }
    }
}

@Composable
fun LogoAuthAndRegister(
    modifier: Modifier = Modifier,
    authAndRegister: List<String>,
    selectedIndex: Int,
) {
    val isLoginSelected = authAndRegister[selectedIndex] == AuthAndRegister.AUTH

    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        AnimatedVisibility(
            visible = isLoginSelected,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(
                    top = 30.dp,
                    bottom = 31.dp,
                    end = 66.dp,
                    start = 64.dp
                ),
            enter = fadeIn(animationSpec = tween(200)) +
                    slideInVertically(animationSpec = tween(200)) { -it },
            exit = fadeOut(animationSpec = tween(200)) +
                    slideOutVertically(animationSpec = tween(200)) { -it }
        ) {
            Icon(
                painter = painterResource(R.drawable.first_logo_for_auth),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(245.dp, 192.dp)
            )
        }
        AnimatedVisibility(
            visible = !isLoginSelected,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(top = 25.dp),
            enter = fadeIn(animationSpec = tween(200)) +
                    slideInVertically(animationSpec = tween(200)) { -it },
            exit = fadeOut(animationSpec = tween(200)) +
                    slideOutVertically(animationSpec = tween(200)) { -it }
        ) {
            Icon(
                painter = painterResource(R.drawable.second_logo_for_reg),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(281.dp, 229.dp)
            )
        }
    }
}

@Composable
fun TypewriterTextWithCursor(
    text: String,
    modifier: Modifier = Modifier,
    durationMs: Int = 500,
    showCursor: Boolean = true,
    blinkIntervalMs: Long = 500,
    onAnimationEnd: () -> Unit = {}
) {
    var displayedText by remember { mutableStateOf("") }
    var showCursorBlink by remember { mutableStateOf(true) }
    var isAnimating by remember { mutableStateOf(false) }
    var previousText by remember { mutableStateOf("") }

    LaunchedEffect(isAnimating, showCursor, displayedText == text) {
        if (!isAnimating && showCursor && displayedText == text) {
            while (true) {
                delay(blinkIntervalMs)
                showCursorBlink = !showCursorBlink
            }
        }
    }

    LaunchedEffect(text) {
        if (text == previousText) return@LaunchedEffect

        isAnimating = true
        showCursorBlink = true

        if (displayedText.isNotEmpty()) {
            val deleteDelay = (durationMs / 2) / maxOf(displayedText.length, 1).toLong()

            for (i in displayedText.length downTo 0) {
                displayedText = displayedText.substring(0, i)
                delay(deleteDelay)
            }
        }

        displayedText = ""
        val charDelay = durationMs / maxOf(text.length, 1).toLong()

        for (i in 0..text.length) {
            displayedText = text.substring(0, i)
            delay(charDelay)
        }

        previousText = text
        isAnimating = false
        onAnimationEnd()
    }

    val finalText = buildString {
        append(displayedText)
    }

    Text(
        text = finalText,
        color = siriumColors.material.onSecondary,
        style = siriumTypography.material.headlineSmall,
        modifier = modifier
    )
}

@Composable
private fun Or() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = siriumColors.material.background,
                    shape = RoundedCornerShape(8.dp)
                )
                .weight(1f)
                .height(1.dp)
        )
        Text(
            text = stringResource(R.string.or),
            color = siriumColors.textSecondary,
            style = siriumTypography.material.bodySmall
        )
        Box(
            modifier = Modifier
                .background(
                    color = siriumColors.material.background,
                    shape = RoundedCornerShape(8.dp)
                )
                .weight(1f)
                .height(1.dp)
        )
    }
}

object AuthAndRegister{
    const val AUTH = "Вход"
    const val REGISTER = "Регистрация"
}