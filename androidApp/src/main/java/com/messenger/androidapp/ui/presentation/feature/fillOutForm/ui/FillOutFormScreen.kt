package com.messenger.androidapp.ui.presentation.feature.fillOutForm.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.approutes.AppRoutes
import com.messenger.androidapp.ui.presentation.shared.button.SiriumBtn
import com.messenger.androidapp.ui.presentation.shared.button.SiriumCheckBox
import com.messenger.androidapp.ui.presentation.shared.text.SiriumTextField
import com.messenger.androidapp.ui.presentation.shared.text.TextFieldWithLabel
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography
import com.messenger.androidapp.ui.theme.typography.montserrat

@Preview
@Composable
private fun PreviewFillOutForm() {
    FillOutForm(
        rememberNavController(),
        siriumColors.material.onPrimary
    )
}

@Composable
fun FillOutFormScreen(
    navController: NavHostController,
    contentColor: Color
) {
    FillOutForm(
        navController = navController,
        contentColor = contentColor
    )
}
@Composable
fun FillOutForm(
    navController: NavHostController,
    contentColor: Color
) {
    var isSelectedCheckBox by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(contentColor)
            .padding(
                top = 16.5.dp,
                start = 24.dp,
                end = 24.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Content(
            modifier = Modifier
                .fillMaxWidth(),
            name = name,
            lastName = lastName,
            password = password,
            repeatPassword = repeatPassword,
            onNameChange = { name = it},
            onLastNameChange = { lastName = it },
            onPasswordChange = { password = it },
            onRepeatPasswordChange = { repeatPassword = it },
        )
        PrivacyPoliceAndButton(
            modifier = Modifier
                .fillMaxWidth(),
            isSelectedCheckBox = isSelectedCheckBox,
            onTermsClick = {},
            onPrivacyClick = {},
            openSpecifyBirthday = {
                if (isSelectedCheckBox){
                    navController.navigate(AppRoutes.SPECIFY_BIRTHDAY)
                }
            },
            onCheck = {
                isSelectedCheckBox = !isSelectedCheckBox
            },
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    name: String,
    lastName: String,
    password: String,
    repeatPassword: String,
    onNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.fill_out_form),
            color = Color.Black,
            style = siriumTypography.material.headlineSmall
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldWithLabel(
                text = name,
                onTextFieldChange = onNameChange,
                label = stringResource(R.string.enter_name),
                placeholder = stringResource(R.string.placeholder_name)
            )
            TextFieldWithLabel(
                text = lastName,
                onTextFieldChange = onLastNameChange,
                label = stringResource(R.string.enter_name),
                placeholder = stringResource(R.string.placeholder_name)
            )
            TextFieldWithLabel(
                text = password,
                onTextFieldChange = onPasswordChange,
                label = stringResource(R.string.enter_name),
                placeholder = stringResource(R.string.placeholder_name),
                keybOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            SiriumTextField(
                text = repeatPassword,
                onTextFieldChange = onRepeatPasswordChange,
                placeholder = stringResource(R.string.placeholder_repeat_password),
                keybOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
    }
}

@Composable
fun PrivacyPoliceAndButton(
    modifier: Modifier = Modifier,
    isSelectedCheckBox: Boolean,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    openSpecifyBirthday: () -> Unit,
    onCheck: () -> Unit
) {
    val primary = siriumColors.material.primary

    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = siriumColors.material.onSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = montserrat
            )
        ) {
            append("Я прочитал и согласен с ")
        }

        withAnnotation(tag = "URL", annotation = "terms") {
            withStyle(
                style = SpanStyle(
                    color = primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = montserrat
                )
            ) {
                append("Правилами и условиями")
            }
        }

        withStyle(
            style = SpanStyle(
                color = siriumColors.material.onSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = montserrat
            )
        ) {
            append(", а также с ")
        }

        withAnnotation(tag = "URL", annotation = "privacy") {
            withStyle(
                style = SpanStyle(
                    color = primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = montserrat
                )
            ) {
                append("Политикой конфиденциальности")
            }
        }
    }


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SiriumCheckBox(
                isSelected = isSelectedCheckBox,
                shape = 4.dp,
                onClick = onCheck
            )
            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    annotatedText.getStringAnnotations(
                        tag = "URL",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let { annotation ->
                        when (annotation.item) {
                            "terms" -> onTermsClick()
                            "privacy" -> onPrivacyClick()
                        }
                    }
                },
            )
        }
        SiriumBtn(
            textBtn = stringResource(R.string.btn_next),
            onClick = openSpecifyBirthday,
            isFilledBtn = true
        )
    }
}