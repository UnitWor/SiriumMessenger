package com.messenger.androidapp.ui.presentation.feature.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography

@Preview
@Composable
private fun PreviewMain() {
    MainScreen()
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Content()
}
@Composable
fun Content() {
    val list = listOf(
        "dfjkgnsjgnslkgnskldgsklgdfngjsdnkglsnm",
        "gdfsgjsoihgsiogjsdgsd;ogjds",
        "gsdkjgiosjgiosdjgiosjgiosjgoisjgiosdhgioudsngsnhgcvmb,dfnlknbklnbklnbklbnzlkbiosn",
        "dsjghsjgbsiughbsiugsniogdsgiosjo",
        "gsbgoisjgopkpkpmlkfsdmnklgnsdklgnsdklgsdnkglmsdgklmdskglmsnlkgnklnlk",
        "gsgosingsoigjisdognjsion",
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
        items(list) {
            Box() {
                Text(
                    text = it
                )
            }
        }
    }
}

@Composable
fun Card(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .background(
                color = siriumColors.material.background,
                shape = RoundedCornerShape(16.dp)
            )
    ) {

    }
}

@Composable
fun PhotoCard(
    photos: List<String>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(152.dp)
    ) {
        CountPhotos(
            photos = photos
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = photos,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun CountPhotos(
    modifier: Modifier = Modifier,
    photos: List<String>
) {
    Box(
        modifier = modifier
            .background(
                color = Color.Black.copy(.5f),
                shape = RoundedCornerShape(8.dp)
            )
    ){
        Text(
            text = "${photos.first()}/${photos.size}",
            color = Color.White,
            style = siriumTypography.extraExtraSmall,
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 3.dp)
        )
    }
}

@Composable
fun UserContent() {
    Column() {

    }
}

@Composable
fun User(
    modifier: Modifier = Modifier
) {
    Row() {

    }
}
