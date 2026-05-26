package com.messenger.androidapp.ui.presentation.feature.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.shared.icon.SiriumIcon
import com.messenger.androidapp.ui.presentation.shared.text.SiriumIconButton
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography
import kotlinx.coroutines.delay

data class MainScreen(
    val id: Int,
    val photos: List<String>,
    val userName: String,
    val userLastName: String,
    val description: String,
)

@Preview
@Composable
private fun PreviewMain() {
    MainScreen()
}

@Composable
fun MainScreen() {
    Content(
        onLike = {}
    )
}
@Composable
fun Content(
    onLike: (Int) -> Unit
) {

    val cards = listOf(
        MainScreen(
            id = 1,
            photos = listOf("https://i.pinimg.com/originals/e8/82/67/e88267a222de3b152d6aced055fc84a7.jpg"),
            userName = "Иван",
            userLastName = "Дружинин",
            description = "Это просто жесть...dsmg;lsmgl;sm;glsdhfjkhkjsdhgjkhsjkghsdjkhjkghsdkjghdskjhgjljlsml;gmds;l когда это видишь вживую то это так захватывает твою душу"
        ),
        MainScreen(
            id = 2,
            photos = listOf("https://i.pinimg.com/originals/e8/82/67/e88267a222de3b152d6aced055fc84a7.jpg"),
            userName = "Иван",
            userLastName = "Дружинин",
            description = "Это просто жесть... sdgmslmgl;smd;lgmsg;lmsdl;gmldsmg;lsml;gкогда это видишь вживую то это так захватывает твою душу"
        ),
        MainScreen(
            id = 3,
            photos = listOf("https://i.pinimg.com/originals/e8/82/67/e88267a222de3b152d6aced055fc84a7.jpg"),
            userName = "Иван",
            userLastName = "Дружинин",
            description = "Это просто жесть... когда это видишь вживую то это так захватывает твою душу"
        ),
        MainScreen(
            id = 4,
            photos = listOf("https://i.pinimg.com/originals/e8/82/67/e88267a222de3b152d6aced055fc84a7.jpg","https://i.pinimg.com/originals/e8/82/67/e88267a222de3b152d6aced055fc84a7.jpg"),
            userName = "Иван",
            userLastName = "Дружинин",
            description = "Это просто жесть... ds,gnklgjskljglsdjlkgsj когда это видишь вживую то это так захватывает твою душу"
        )
    )

    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 19.dp,
            end = 17.dp,
            top = 3.dp,
            bottom = 3.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        verticalItemSpacing = 13.dp,
    ) {
        items(cards) { card ->
            Card(
                photos = card.photos,
                userName = card.userName,
                userLastName = card.userLastName,
                onLike = { onLike(card.id) },
                description = card.description,
                openPostScreen = {  }
            )
        }
    }
}

@Composable
fun Card(
    modifier: Modifier = Modifier,
    photos: List<String>,
    userName: String,
    userLastName: String,
    description: String,
    onLike: () -> Unit,
    openPostScreen: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(onClick = openPostScreen)
            .background(
                color = siriumColors.material.background,
                shape = RoundedCornerShape(16.dp)
            ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PhotoCard(
            photos = photos
        )
        UserContent(
            userName = userName,
            userLastName = userLastName,
            description = description
        )
        ActionCard(
            onLike = onLike
        )
    }
}

@Composable
fun PhotoCard(
    photos: List<String>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { photos.size }

    val indicatorVisibilityTimer = 1000L
    val isIndicatorVisible = remember { mutableStateOf(false) }

    LaunchedEffect(isIndicatorVisible.value, pagerState.currentPage) {
        if (isIndicatorVisible.value) {
            delay(indicatorVisibilityTimer)
            isIndicatorVisible.value = false
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(152.dp)
    ) {
        if (photos.size > 1) {
            CountPhotos(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 9.dp, end = 11.dp),
                photos = photos,
                index = pagerState.currentPage
            )
        }
        if (photos.size in 0..1){
            Photo(
                photo = photos.firstOrNull().orEmpty()
            )
        } else {
            Pager(
                state = pagerState,
                photos = photos,
                onPress = { isIndicatorVisible.value = true }
            )
        }
    }
}

@Composable
fun Photo(
    modifier: Modifier = Modifier,
    photo: String
) {
    AsyncImage(
        model = photo,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun BoxScope.Pager(
    modifier: Modifier = Modifier,
    state: PagerState,
    photos: List<String>,
    onPress: () -> Unit
) {
    HorizontalPager(
        modifier = modifier
            .matchParentSize()
            .pointerInput(PointerEventType.Press) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        if (event.type == PointerEventType.Press) onPress()
                    }
                }
            },
        state = state
    ) { page ->
        Photo(
            photo = photos[page]
        )
    }
}

@Composable
fun CountPhotos(
    modifier: Modifier = Modifier,
    photos: List<String>,
    index: Int
) {
    Box(
        modifier = modifier
            .background(
                color = Color.Black.copy(.5f),
                shape = RoundedCornerShape(8.dp)
            )
    ){
        Text(
            text = "${index + 1}/${photos.size}",
            color = Color.White,
            style = siriumTypography.extraExtraSmall,
            modifier = Modifier.padding(vertical = 3.dp, horizontal = 6.dp)
        )
    }
}

@Composable
fun UserContent(
    userName: String,
    userLastName: String,
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.5.dp),
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        User(
            userName = userName,
            userLastName = userLastName
        )
        Text(
            text = description,
            color = siriumColors.textSecondary,
            style = siriumTypography.material.labelSmall
        )
    }
}

@Composable
fun User(
    modifier: Modifier = Modifier,
    userName: String,
    userLastName: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SiriumIcon(
            icon = R.drawable.ic_profile,
            tint = Color.White,
            sizeIcon = 16.dp,
            paddingEnd = 4.dp,
            paddingTop = 4.dp,
            paddingStart = 4.dp,
            paddingBottom = 4.dp
        )
        Text(
            text = "$userName $userLastName",
            color = siriumColors.material.onSecondary,
            style = siriumTypography.material.labelSmall
        )
    }
}

@Composable
fun ActionCard(
    modifier: Modifier = Modifier,
    onLike: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 11.dp, end = 11.dp, bottom = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
         SiriumIconButton(
             onClick = onLike,
             icon = R.drawable.ic_like,
             backColor = siriumColors.material.background,
             padding = 3.dp,
             sizeIcon = 16.dp,
         )
        SiriumIconButton(
            onClick = onLike,
            icon = R.drawable.ic_repost,
            padding = 5.dp,
            backColor = siriumColors.material.background,
            sizeIcon = 12.dp,
        )
    }
}