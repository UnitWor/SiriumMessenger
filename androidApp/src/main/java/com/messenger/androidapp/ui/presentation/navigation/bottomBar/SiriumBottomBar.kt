package com.messenger.androidapp.ui.presentation.navigation.bottomBar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.presentation.approutes.AppRoutes
import com.messenger.androidapp.ui.theme.SiriumTheme
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography
import io.github.fletchmckee.liquid.LiquidState
import io.github.fletchmckee.liquid.liquefiable
import io.github.fletchmckee.liquid.liquid
import io.github.fletchmckee.liquid.rememberLiquidState

@Preview
@Composable
private fun PrevBottomBar() {
    val liquidState = rememberLiquidState()

    SiriumTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(siriumColors.material.background)
        ) {
            // 1. Фоновый контент (список чатов)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    // Помечаем фон, как источник для эффекта стекла
                    .liquefiable(liquidState)
            ) {
                items(20) { index ->
                    // Карточка чата в стиле Telegram [citation:2][citation:8]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = siriumColors.material.surface
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Аватар
                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .clip(CircleShape)
                                    .background(siriumColors.material.primary),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "A",
                                    color = siriumColors.material.onPrimary
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            // Информация о чате
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Иван Дружинин",
                                    style = siriumTypography.material.titleMedium
                                )
                                Text(
                                    text = "Это просто жесть... когда это видишь вживую...",
                                    style = siriumTypography.material.bodyMedium,
                                    color = siriumColors.textSecondary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            // Время и счетчик
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "1/2",
                                    style = siriumTypography.extraExtraSmall
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .background(siriumColors.material.primary),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "5",
                                        style = siriumTypography.extraExtraSmall,
                                        color = siriumColors.material.onPrimary
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // 2. BottomBar с эффектом Liquid Glass
            SiriumBottomBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                liquidState = liquidState,
                navController = rememberNavController()
            )
        }
    }
}

@Composable
fun SiriumBottomBar(
    modifier: Modifier = Modifier,
    liquidState: LiquidState,
    navController: NavHostController
) {
    val bottomBarItem = listOf(
        BottomBarItemCLass(
            icon = R.drawable.ic_profile,
            route = AppRoutes.PROFILE,
        ),
        BottomBarItemCLass(
            icon = R.drawable.ic_setting,
            route = AppRoutes.SETTING,
        ),
        BottomBarItemCLass(
            icon = R.drawable.ic_message,
            route = AppRoutes.MESSAGE,
        ),
        BottomBarItemCLass(
            icon = R.drawable.ic_search,
            route = AppRoutes.SEARCH,
        ),
        BottomBarItemCLass(
            icon = R.drawable.ic_burger,
            route = AppRoutes.OTHER,
        ),
    )


    Row(
        modifier = modifier
            .padding(bottom = 34.dp)
            .liquid(liquidState = liquidState) {
                frost = 5.dp
                shape = RoundedCornerShape(32.dp)
                refraction = 0.35f
                edge = 0.1f
                curve = 0.55f
//                tint = Color.White.copy(0.5f)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        bottomBarItem.forEach { item ->
            val currentRoute = navController
                .currentBackStackEntryAsState()
                .value
                ?.destination
                ?.route

            val isSelected = currentRoute == item.route
            BottomBarItem(
                icon = item.icon,
                onClick = {
                    item.route.let { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                isSelected = isSelected
            )
        }
    }
}

@Composable
fun BottomBarItem(
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color by animateColorAsState(
        targetValue = if (isSelected) siriumColors.material.primary else Color.Unspecified,
        animationSpec = tween(400)
    )

    Box(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            tint = color,
            modifier = Modifier
                .padding(
                    start = 14.dp,
                    bottom = 15.dp,
                    end = 13.2.dp,
                    top = 16.dp
                )
        )
    }
}

data class BottomBarItemCLass(
    val icon: Int,
    val route: String,
)