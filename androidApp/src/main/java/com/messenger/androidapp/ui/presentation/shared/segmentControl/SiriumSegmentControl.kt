package com.messenger.androidapp.ui.presentation.shared.segmentControl

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.messenger.androidapp.R
import com.messenger.androidapp.ui.theme.siriumColors
import com.messenger.androidapp.ui.theme.siriumTypography
import kotlin.math.abs
import kotlin.math.roundToInt

@Preview
@Composable
private fun PrevSiriumSegmentControl() {
    var segment by remember { mutableIntStateOf(0) }

    SiriumSegmentControl(
        options = listOf("Вход", "Регистрация"),
        selectedIndex = segment,
        onSelectedChanged = { segment = it }
    )
}

@Composable
fun SiriumSegmentControl(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedIndex: Int,
    topPadding: Dp = 2.dp,
    bottomPadding: Dp = 2.dp,
    onSelectedChanged: (Int) -> Unit,
) {
    val itemCount = options.size
    val density = LocalDensity.current

    var itemWidth by remember { mutableFloatStateOf(0f) }
    var dragTargetIndex by remember { mutableIntStateOf(selectedIndex) }
    var dragOffset by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }

    LaunchedEffect(selectedIndex) {
        if (!isDragging) {
            dragTargetIndex = selectedIndex
        }
    }

    val animatedOffset by animateFloatAsState(
        targetValue = dragTargetIndex * itemWidth,
        animationSpec = if (!isDragging) tween(durationMillis = 250, easing = FastOutSlowInEasing) else snap()
    )

    val indicatorOffset = if (isDragging) {
        (dragTargetIndex * itemWidth + dragOffset).coerceIn(0f, itemWidth * (itemCount - 1))
    } else {
        animatedOffset
    }

    val startPadding = when (options.size) {
        4 -> 9.dp
        3 -> 4.dp
        else -> 2.dp
    }

    val endPadding = when (options.size) {
        4 -> 4.dp
        3 -> 4.dp
        else -> 1.dp
    }

    Box(
        modifier = modifier
            .background(
                color = siriumColors.material.background,
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .padding(
                    top = topPadding,
                    start = startPadding,
                    end = endPadding,
                    bottom = bottomPadding
                )
        ) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(indicatorOffset.roundToInt(), 0) }
                    .height(36.dp)
                    .width(with(density) { itemWidth.toDp() })
                    .shadow(2.dp, RoundedCornerShape(16.dp))
                    .background(
                        color = siriumColors.material.primary,
                        shape = RoundedCornerShape(16.dp)
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .onGloballyPositioned { layoutCoordinates ->
                        itemWidth = layoutCoordinates.size.width.toFloat() / itemCount
                    }
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures(
                            onDragStart = {
                                isDragging = true
                            },
                            onDragEnd = {
                                isDragging = false
                                dragOffset = 0f
                                if (dragTargetIndex != selectedIndex) {
                                    onSelectedChanged(dragTargetIndex)
                                }
                            },
                            onDragCancel = {
                                isDragging = false
                                dragOffset = 0f
                                dragTargetIndex = selectedIndex
                            },
                            onHorizontalDrag = { _, dragAmount ->
                                dragOffset += dragAmount
                                val dragThreshold = itemWidth / 3f

                                when {
                                    dragOffset > dragThreshold && dragTargetIndex < itemCount - 1 -> {
                                        dragTargetIndex++
                                        dragOffset -= itemWidth // Корректируем offset чтобы не было прыжка
                                    }
                                    dragOffset < -dragThreshold && dragTargetIndex > 0 -> {
                                        dragTargetIndex--
                                        dragOffset += itemWidth // Корректируем offset чтобы не было прыжка
                                    }
                                }
                            }
                        )
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                options.forEachIndexed { index, option ->
                    val indicatorStart = if (isDragging) {
                        dragTargetIndex * itemWidth + dragOffset
                    } else {
                        dragTargetIndex * itemWidth
                    }
                    val indicatorEnd = indicatorStart + itemWidth

                    val itemStart = index * itemWidth
                    val itemEnd = itemStart + itemWidth

                    val overlapStart = maxOf(indicatorStart, itemStart)
                    val overlapEnd = minOf(indicatorEnd, itemEnd)
                    val overlapWidth = maxOf(0f, overlapEnd - overlapStart)
                    val overlapFraction = (overlapWidth / itemWidth).coerceIn(0f, 1f)

                    val activeColor = siriumColors.material.onPrimary
                    val inactiveColor = siriumColors.textSecondary

                    val colorText = lerp(inactiveColor, activeColor, overlapFraction)

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                if (!isDragging) {
                                    dragTargetIndex = index
                                    onSelectedChanged(index)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = option,
                            color = colorText,
                            style = siriumTypography.extraSmallMedium,
                            modifier = Modifier.padding(top = 11.dp, bottom = 10.dp)
                        )
                    }
                }
            }
        }
    }
}