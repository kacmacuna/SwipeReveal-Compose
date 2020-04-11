package com.example.swipereveal

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.*
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.ScaleFit
import androidx.ui.layout.*
import androidx.ui.layout.RowScope.gravity
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.text.TextStyle
import androidx.ui.text.style.TextAlign
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import androidx.ui.unit.toPx
import com.example.swipereveal.model.SwipeActionButton
import com.example.swipereveal.model.SwipeGravity
import com.example.swipereveal.model.SwipePosition
import com.example.swipereveal.sample.DraggableSample


@Composable
fun SwipeReveal(
    layoutHeight: Dp,
    swipeActionButton: Collection<SwipeActionButton>,
    children: @Composable() () -> Unit
) {
    val swipePosition = remember { SwipePosition(0f, 0f, 0f) }

    Box(
        modifier = Modifier
            .swipable(swipePosition)
            .preferredHeight(layoutHeight)
            .fillMaxHeight()
    ) {
        SwipableBody(
            layoutHeight,
            swipeActionButton,
            swipePosition,
            children
        )
    }

}

@Composable
private fun SwipableBody(
    layoutHeight: Dp,
    swipeActionButtons: Collection<SwipeActionButton>,
    swipePosition: SwipePosition,
    children: @Composable() () -> Unit
) {
    val xOffset = with(DensityAmbient.current) { swipePosition.position.toDp() }
    SetActionButtons(layoutHeight, swipeActionButtons.filter { it.gravity == SwipeGravity.End }) {
        swipePosition.minLeft = -it.size.width.toPx().value
    }
    SetActionButtons(layoutHeight, swipeActionButtons.filter { it.gravity == SwipeGravity.Start }) {
        swipePosition.maxRight = it.size.width.toPx().value
    }
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
    swipeActionButtons: Collection<SwipeActionButton>,
    onPositioned: (LayoutCoordinates) -> Unit
) {
    if (swipeActionButtons.isEmpty())
        return
    Box(
        gravity = swipeActionButtons.first().gravity.toContentGravity(),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            arrangement = Arrangement.End,
            modifier = Modifier
                .preferredHeight(layoutHeight)
                .drawBackground(color = Color.Cyan)
                .onPositioned(onPositioned)
                .gravity(RowAlign.Center)
        ) {
            swipeActionButtons.forEach {
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
                        arrangement = Arrangement.Center,
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
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                color = it.textColor
                            ),
                            modifier = Modifier
                                .wrapContentHeight()
                                .preferredWidthIn(60.dp, 80.dp)
                                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                                .gravity(ColumnAlign.Center)
                        )
                    }
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