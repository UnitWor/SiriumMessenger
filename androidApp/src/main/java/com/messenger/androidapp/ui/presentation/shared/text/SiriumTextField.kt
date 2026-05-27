package com.messenger.androidapp.ui.presentation.shared.text

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography

@Preview
@Composable
private fun PrevTextField() {
    var text by remember { mutableStateOf("") }
    var search by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SiriumTextField(
            modifier = Modifier,
            text = text,
            onTextFieldChange = { text = it }
        )
        SearchTextField(
            search = search
        ) { search = it }

        TextFieldWithLabel(
            text = name,
            onTextFieldChange = { name = it },
            label = "Имя",
            placeholder = "Имя"
        )
        PasswordTextField(
            password = password,
            onPasswordChange = { password = it }
        )
        MessageTextField(
            onAddedFiles = {},
            onSendMessage = {},
            onSendVoice = {},
            message = message,
            onMessageChange = { message = it }
        )
    }
}


@Composable
fun MessageTextField(
    modifier: Modifier = Modifier,
    onAddedFiles: () -> Unit,
    onSendMessage: () -> Unit,
    onSendVoice: () -> Unit,
    message: String,
    onMessageChange: (String) -> Unit
) {
    val verticalPadding by animateDpAsState(
        targetValue = if (message.isEmpty()) 15.5.dp else 7.dp,
    )
    val horizontalPadding by animateDpAsState(
        targetValue = if (message.isEmpty()) 16.dp else 6.dp,
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        SiriumIconButton(
            icon = R.drawable.ic_add,
            backColor = Color.Unspecified,
            padding = 8.dp,
            onClick = onAddedFiles
        )
        CustomTextField(
            modifier = Modifier
                .weight(1f),
            text = message,
            onTextFieldChange = onMessageChange,
            boxShape = CircleShape,
            arrangementSpacer = 12.dp,
            keybOptions = KeyboardOptions(
                autoCorrect = true,
                capitalization = KeyboardCapitalization.Words
            ),
            startPadding = 16.dp,
            endPadding = horizontalPadding,
            topPadding = verticalPadding,
            bottomPadding = verticalPadding,
            trailingIcon = {
                AnimatedVisibility(
                    visible = message.isNotEmpty(),
                    enter = fadeIn() + scaleIn(),
                    exit = fadeOut() + scaleOut()
                ) {
                    SiriumIconButton(
                        icon = R.drawable.ic_send,
                        padding = 10.dp,
                        sizeIcon = 12.dp,
                        onClick = onSendMessage
                    )
                }
            },
        )
        SiriumIconButton(
            icon = R.drawable.ic_micro,
            padding = 6.dp,
            onClick = onSendVoice
        )
    }
}

@Composable
fun SiriumIconButton(
    modifier: Modifier = Modifier,
    icon: Int,
    onClick: () -> Unit,
    shape: Shape = CircleShape,
    backColor: Color = siriumColors.material.primary,
    padding: Dp,
    tint: Color = Color.Unspecified,
    sizeIcon: Dp = 20.dp
) {
    Box(
        modifier = modifier
            .background(
                color = backColor,
                shape = shape
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .padding(padding)
                .size(sizeIcon)
        )
    }
}

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    search: String,
    onSearchChange: (String) -> Unit
) {
    val backgroundColor =
        if(search.isNotEmpty()) siriumColors.backSecondary3 else siriumColors.material.background

    CustomTextField(
        text = search,
        onTextFieldChange = onSearchChange,
        topPadding = 12.dp,
        bottomPadding = 12.dp,
        placeholder = "Search",
        modifier = modifier,
        keybOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words
        ),
        modifierText = Modifier.fillMaxWidth(),
        boxColor = backgroundColor,
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                contentDescription = null,
                tint = if (search.isNotEmpty()) siriumColors.material.onSecondary else siriumColors.textSecondary,
                modifier = Modifier.size(20.dp)
            )
        }
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    password: String,
    onPasswordChange: (String) -> Unit
) {
    var visiblePassword by remember { mutableStateOf(false) }

    val transform = if (visiblePassword) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation('*')
    }
    val icon = if (visiblePassword) R.drawable.ic_eye else R.drawable.ic_close_eye
    SiriumTextField(
        text = password,
        modifier = modifier,
        onTextFieldChange = onPasswordChange,
        transformation = transform,
        placeholder = "Пароль",
        keybOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        arrangementSpacer = 8.dp,
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.clickable { visiblePassword = !visiblePassword }
            )
        }
    )
}

@Composable
fun TextFieldWithLabel(
    modifier: Modifier = Modifier,
    modifierTextField: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    placeholder: String,
    keybOptions: KeyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
    onTextFieldChange: (String) -> Unit,
    label: String,
    decorationModifier: Modifier = Modifier,
) {
    LabelAndContent(
        modifier = modifier.fillMaxWidth(),
        label = label
    ) {
        SiriumTextField(
            modifier = modifierTextField,
            text = text,
            enabled = enabled,
            keybOptions = keybOptions,
            onTextFieldChange = onTextFieldChange,
            placeholder = placeholder,
            decorationModifier = decorationModifier,
            singleLine = singleLine,
        )
    }
}

@Composable
fun LabelAndContent(
    modifier: Modifier = Modifier,
    label: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            color = siriumColors.backSecondary3,
            style = siriumTypography.extraSmallMedium
        )
        content()
    }
}

@Composable
fun SiriumTextField(
    modifier: Modifier = Modifier,
    modifierText: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    isError: Boolean = false,
    topPadding: Dp = 16.dp,
    bottomPadding: Dp = 16.dp,
    startPadding: Dp = 16.dp,
    endPadding: Dp = 16.dp,
    arrangementSpacer: Dp = 16.dp,
    singleLine: Boolean = false,
    readOnly: Boolean = false,
    boxShape: Shape = RoundedCornerShape(16.dp),
    fontWeight: FontWeight = FontWeight.Normal,
    placeholder: String = "",
    stylePlaceholder: TextStyle = siriumTypography.material.bodySmall,
    font: Int = R.font.montserrat_regular,
    keybOptions: KeyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
    keybAction: KeyboardActions = KeyboardActions.Default,
    transformation: VisualTransformation = VisualTransformation.None,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    onTextFieldChange: (String) -> Unit,
    decorationModifier: Modifier = Modifier,
) {

    val backgroundColor = if (isError) siriumColors.material.error.copy(0.2f)
    else if(text.isNotEmpty() && placeholder.isEmpty()) siriumColors.backSecondary3
    else siriumColors.material.background

    CustomTextField(
        modifier = modifier,
        modifierText = modifierText,
        text = text,
        enabled = enabled,
        topPadding = topPadding,
        bottomPadding = bottomPadding,
        startPadding = startPadding,
        endPadding = endPadding,
        arrangementSpacer = arrangementSpacer,
        singleLine = singleLine,
        readOnly = readOnly,
        boxColor = backgroundColor,
        boxShape = boxShape,
        fontWeight = fontWeight,
        placeholder = placeholder,
        stylePlaceholder = stylePlaceholder,
        font = font,
        keybOptions = keybOptions,
        keybAction = keybAction,
        transformation = transformation,
        maxLines = maxLines,
        decorationModifier = decorationModifier,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        onTextFieldChange = onTextFieldChange
    )
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    modifierText: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    topPadding: Dp = 16.dp,
    bottomPadding: Dp = 16.dp,
    startPadding: Dp = 16.dp,
    endPadding: Dp = 16.dp,
    arrangementSpacer: Dp = 16.dp,
    singleLine: Boolean = false,
    readOnly: Boolean = false,
    boxColor: Color = siriumColors.material.error,
    boxShape: Shape = RoundedCornerShape(16.dp),
    fontWeight: FontWeight = FontWeight.Normal,
    placeholder: String = "",
    stylePlaceholder: TextStyle = siriumTypography.material.bodySmall,
    font: Int = R.font.montserrat_regular,
    keybOptions: KeyboardOptions = KeyboardOptions.Default,
    keybAction: KeyboardActions = KeyboardActions.Default,
    transformation: VisualTransformation = VisualTransformation.None,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    onTextFieldChange: (String) -> Unit,
    decorationModifier: Modifier = Modifier,
) {

    val backColor by animateColorAsState(
        targetValue = boxColor,
        animationSpec = tween(500)
    )

    Row(
        modifier = modifier
            .background(color = backColor, shape = boxShape)
            .padding(
                top = topPadding,
                end = endPadding,
                bottom = bottomPadding,
                start = startPadding,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(arrangementSpacer)
    ) {
        leadingIcon?.invoke()
        CustomBasicTextField(
            text = text,
            onTextFieldChange = onTextFieldChange,
            singleLine = singleLine,
            modifier = modifierText.weight(1f),
            enabled = enabled,
            readOnly = readOnly,
            fontWeight = fontWeight,
            maxLines = maxLines,
            placeholder = placeholder,
            stylePlaceholder = stylePlaceholder,
            font = font,
            decorationModifier = decorationModifier,
            keyboardOptions = keybOptions,
            keyboardAction = keybAction,
            transformation = transformation,
        )
        trailingIcon?.invoke()
    }
}

@Composable
fun CustomBasicTextField(
    modifier: Modifier = Modifier,
    decorationModifier: Modifier = Modifier,
    text: String,
    onTextFieldChange: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    fontWeight: FontWeight = FontWeight.Normal,
    placeholder: String = "",
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    stylePlaceholder: TextStyle = siriumTypography.material.bodySmall,
    font: Int = R.font.montserrat_regular,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardAction: KeyboardActions = KeyboardActions.Default,
    transformation: VisualTransformation = VisualTransformation.None,
    contentAlignment: Alignment = Alignment.TopStart
) {
    BasicTextField(
        modifier = modifier,
        value = text,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = TextStyle(
            color = siriumColors.material.onSecondary,
            fontSize = 12.sp,
            fontWeight = fontWeight,
            fontFamily = FontFamily(Font(font, fontWeight)),
        ),
        onValueChange = onTextFieldChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardAction,
        visualTransformation = transformation,
        singleLine = singleLine,
        maxLines = maxLines,
        cursorBrush = SolidColor(siriumColors.material.primary),
        decorationBox = { innerTextField ->
            Box(
                modifier = decorationModifier,
                contentAlignment = contentAlignment
            ) {
                if (text.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = siriumColors.textSecondary,
                        style = stylePlaceholder
                    )
                }
            }
            innerTextField()
        }
    )
}