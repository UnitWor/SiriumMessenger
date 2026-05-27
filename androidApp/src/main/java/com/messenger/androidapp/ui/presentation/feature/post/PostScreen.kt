package com.messenger.androidapp.ui.presentation.feature.post

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.feature.main.ui.Photo
import com.messenger.androidapp.ui.presentation.feature.main.ui.User
import com.messenger.androidapp.ui.presentation.shared.text.SiriumIconButton
import com.messenger.androidapp.ui.presentation.shared.text.TextFieldWithLabel
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography

@Preview
@Composable
private fun PreviewPost() {
    Content(siriumColors.material.onPrimary)
}

@Composable
fun PostScreen(
    contentColor: Color
) {
    Content(
        contentColor = contentColor
    )
}

@Composable
private fun Content(
    contentColor: Color
) {

    var like by remember { mutableStateOf(0) }
    var repost by remember { mutableStateOf(0) }
    var comment by remember { mutableStateOf("") }
    var isSelectedLike by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(contentColor)
            .padding(
                horizontal = 24.dp
            )
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        PostPhoto(
            photo = "https://i.pinimg.com/originals/e8/82/67/e88267a222de3b152d6aced055fc84a7.jpg"
        )
        Text(
            text = "10.12.2026",
            color = siriumColors.textSecondary,
            style = siriumTypography.extraExtraSmall
        )
        User(
            userName = "Иван",
            userLastName = "Дружинин",
            sizeIcon = 20.dp,
            paddingEnd = 10.dp,
            paddingTop = 10.dp,
            paddingStart = 10.dp,
            paddingBottom = 10.dp,
            styleText = siriumTypography.material.labelLarge,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        )
        Text(
            text = "Это просто жесть... когда это видишь вживую то это так захватывает твою душу и потом оно все заревало",
            color = siriumColors.textSecondary,
            style = siriumTypography.material.bodyLarge
        )
        PostActions(
            onLike = {
                isSelectedLike = !isSelectedLike
                like += 1
            },
            onRepost = { repost += 1 },
            countLike = like,
            countRepost = repost,
            isSelectedLike = isSelectedLike
        )
        TextFieldWithLabel(
            modifierTextField = Modifier.height(97.dp),
            text = comment,
            onTextFieldChange = { comment = it },
            singleLine = false,
            decorationModifier = Modifier.fillMaxSize(),
            label = "Напишите комментарий",
            placeholder = "Ваш комментарий"
        )
    }
}

@Composable
fun Comments(
    modifier: Modifier = Modifier
) {
    Column() {

    }
}

@Composable
fun PostPhoto(
    modifier: Modifier = Modifier,
    photo: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    ){
        Photo(
            photo = photo
        )
    }
}

@Composable
fun PostActions(
    modifier: Modifier = Modifier,
    onLike: () -> Unit,
    onRepost: () -> Unit,
    countLike: Int,
    countRepost: Int,
    isSelectedLike: Boolean
) {
    val tint by animateColorAsState(
        targetValue = if (isSelectedLike) siriumColors.material.error else Color.Unspecified,
        animationSpec = tween(300)
    )

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SiriumIconButton(
            icon = R.drawable.ic_like,
            onClick = onLike,
            padding = 7.dp,
            backColor = siriumColors.material.background,
            tint = tint
        )
        Text(
            text = when {
                countLike == 0 -> "00"
                countLike > 9 -> countLike.toString()
                else -> "0$countLike"
            },
            color = siriumColors.textSecondary,
            style = siriumTypography.material.bodySmall
        )
        SiriumIconButton(
            icon = R.drawable.ic_repost,
            onClick = onRepost,
            padding = 7.dp,
            backColor = siriumColors.material.background
        )
        Text(
            text = when {
                countRepost == 0 -> "00"
                countRepost > 9 -> countRepost.toString()
                else -> "0$countRepost"
            },
            color = siriumColors.textSecondary,
            style = siriumTypography.material.bodySmall
        )
    }
}