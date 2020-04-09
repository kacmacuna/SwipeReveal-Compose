package com.example.swipereveal

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.DensityAmbient
import androidx.ui.core.LayoutCoordinates
import androidx.ui.core.Modifier
import androidx.ui.core.onPositioned
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.ScaleFit
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.res.loadVectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.style.TextAlign
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import androidx.ui.unit.toPx
import com.example.swipereveal.model.SwipeActionButton
import com.example.swipereveal.sample.DraggableSample


@Composable
fun SwipeReveal(
    layoutHeight: Dp,
    swipeActionButton: Collection<SwipeActionButton>,
    children: @Composable() () -> Unit
) {
    val position = state { 0f }
    val min = 0.dp

    val minLeft = state { 0F }
    val maxRight = with(DensityAmbient.current) { min.toPx().value }

    Box(
        gravity = ContentGravity.CenterEnd,
        modifier = Modifier
            .swipable(position, minLeft.value, maxRight)
            .preferredHeight(layoutHeight)
            .fillMaxHeight()
    ) {
        SwipableBody(layoutHeight, swipeActionButton, minLeft, position, children)
    }

}

@Composable
private fun SwipableBody(
    layoutHeight: Dp,
    swipeActionButton: Collection<SwipeActionButton>,
    minLeft: MutableState<Float>,
    position: MutableState<Float>,
    children: @Composable() () -> Unit
) {
    SetActionButtons(layoutHeight, swipeActionButton) {
        minLeft.value = -it.size.width.toPx().value
    }
    val xOffset = with(DensityAmbient.current) { position.value.toDp() }
    Box(
        Modifier
            .offset(x = xOffset, y = 0.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        children()
    }
}


@Composable
private fun SetActionButtons(
    layoutHeight: Dp,
    swipeActionButton: Collection<SwipeActionButton>,
    onPositioned: (LayoutCoordinates) -> Unit
) {
    Row(
        arrangement = Arrangement.End,
        modifier = Modifier
            .preferredHeight(layoutHeight)
            .drawBackground(color = Color.Cyan)
            .onPositioned(onPositioned)
    ) {
        swipeActionButton.forEach {
            Button(
                onClick = it.onClick,
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight(),
                elevation = 0.dp,
                shape = RoundedCornerShape(0.dp),
                backgroundColor = it.backgroundColor,
                innerPadding = Button.DefaultInnerPadding.copy(bottom = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .fillMaxHeight()
                        .padding(top = 16.dp, bottom = 16.dp)
                ) {
                    if (it.vector != null)
                        Image(
                            asset = it.vector,
                            modifier = Modifier
                                .preferredSize(24.dp, 24.dp).gravity(ColumnAlign.Center),
                            scaleFit = ScaleFit.FillMinDimension
                        )
                    Text(
                        text = it.actionName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(textAlign = TextAlign.Center, color = it.textColor),
                        modifier = Modifier
                            .wrapContentHeight()
                            .preferredWidthIn(60.dp, 80.dp)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        DraggableSample()
    }
}

