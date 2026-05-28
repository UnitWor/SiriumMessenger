package com.messenger.androidapp.ui.presentation.feature.customize.ui.recommendations

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.approutes.AppRoutes
import com.messenger.androidapp.ui.presentation.feature.secureCode.ui.TitleAndSubTitle
import com.messenger.androidapp.ui.presentation.shared.button.SiriumBtn
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography

data class CustomizeRecommendations(
    val id: Int,
    val recommendation: String,
    val isSelected: Boolean
)

@Preview
@Composable
private fun PreviewCustomizeRecommendation() {
    Content(
        rememberNavController(),
        siriumColors.material.onPrimary
    )
}

@Composable
fun CustomizeRecommendationScreen(
    navController: NavHostController,
    contentColor: Color
) {
    Content(
        navController = navController,
        contentColor = contentColor
    )
}

@Composable
private fun Content(
    navController: NavHostController,
    contentColor: Color
) {

    val recommendations = remember {
        mutableStateListOf(
            CustomizeRecommendations(
                id = 1,
                recommendation = "dfgddhdfhdf",
                isSelected = false
            ),
            CustomizeRecommendations(
                id = 2,
                recommendation = "dfgddhdsdfhgdf",
                isSelected = false
            ),
            CustomizeRecommendations(
                id = 3,
                recommendation = "dfgddhdfhdfgdf",
                isSelected = false
            ),
            CustomizeRecommendations(
                id = 4,
                recommendation = "dfgddhdfhdfhfhgfhfhfhf",
                isSelected = false
            ),
            CustomizeRecommendations(
                id = 5,
                recommendation = "dfgddhdfhdfhfghfh",
                isSelected = false
            ),
            CustomizeRecommendations(
                id = 6,
                recommendation = "dfgddhdfhdfdhdh",
                isSelected = false
            ),
            CustomizeRecommendations(
                id = 7,
                recommendation = "dfgddhdfhdfdfhdh",
                isSelected = false
            ),
            CustomizeRecommendations(
                id = 8,
                recommendation = "dfgddhdfhdfdsgdfgd",
                isSelected = false
            ),
            CustomizeRecommendations(
                id = 9,
                recommendation = "dfgddhdfhdfdhdh",
                isSelected = false
            ),
            CustomizeRecommendations(
                id = 10,
                recommendation = "dfgddhdfhdfdfhdh",
                isSelected = false
            ),
            CustomizeRecommendations(
                id = 11,
                recommendation = "dfgddhdfhdfdsgdfgd",
                isSelected = false
            ),
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(contentColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 21.5.dp),
        ) {
            TitleAndSubTitle(
                title = R.string.title_customize_recommendations,
                subtitle = R.string.subtitle_customize_recommendations,
                textAlign = TextAlign.Start,
                horizAlignment = Alignment.Start
            )
            LazyColumn(
                contentPadding = PaddingValues(top = 32.dp,bottom = 70.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = recommendations,
                    key = { it.id }
                ) { recommendation ->
                    val index = recommendations.indexOf(recommendation)
                    Recommendation(
                        isSelected = recommendation.isSelected,
                        recommendation = recommendation.recommendation,
                        onSelected = {
                            recommendations[index] =
                                recommendation.copy(isSelected = !recommendation.isSelected)
                        }
                    )
                }
            }
        }
        SiriumBtn(
            modifier = Modifier
                .padding(
                    horizontal = 24.dp,
                    vertical = 21.5.dp
                )
                .align(Alignment.BottomCenter),
            textBtn = stringResource(R.string.btn_next),
            isFilledBtn = true,
            onClick = {
                if (recommendations.size >= 1) {
                    navController.navigate(AppRoutes.SUCCESS)
                }
            }
        )
    }
}

@Composable
fun Recommendation(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    recommendation: String,
    onSelected: () -> Unit
) {
    val backColor by animateColorAsState(
        targetValue = if (isSelected) siriumColors.backSecondary3 else siriumColors.material.background,
        animationSpec = tween(300)
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onSelected)
            .background(
                color = backColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                horizontal = 24.dp,
                vertical = 21.5.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = recommendation,
            color = siriumColors.material.onSecondary,
            style = siriumTypography.material.labelMedium
        )
        if (isSelected) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_checkmark),
                contentDescription = null,
                tint = siriumColors.material.primary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}