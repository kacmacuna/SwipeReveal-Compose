package com.example.swipereveal

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.remember
import androidx.compose.state
import androidx.ui.core.*
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
import com.example.swipereveal.model.SwipePosition
import com.example.swipereveal.sample.DraggableSample


@Composable
fun SwipeReveal(
    layoutHeight: Dp,
    swipeActionButton: Collection<SwipeActionButton>,
    swipeGravity: SwipeGravity = SwipeGravity.End,
    children: @Composable() () -> Unit
) {
    val swipePosition = remember { SwipePosition(0f, 0f, 0f) }

    Box(
        gravity = swipeGravity.toContentGravity(),
        modifier = Modifier
            .swipable(swipePosition)
            .preferredHeight(layoutHeight)
            .fillMaxHeight()
    ) {
        SwipableBody(
            layoutHeight,
            swipeGravity,
            swipeActionButton,
            swipePosition,
            children
        )
    }

}

@Composable
private fun SwipableBody(
    layoutHeight: Dp,
    swipeGravity: SwipeGravity,
    swipeActionButton: Collection<SwipeActionButton>,
    swipePosition: SwipePosition,
    children: @Composable() () -> Unit
) {
    SetActionButtons(layoutHeight, swipeActionButton) {
        if (swipeGravity == SwipeGravity.End) {
            swipePosition.minLeft = -it.size.width.toPx().value
        } else {
            swipePosition.maxRight = it.size.width.toPx().value
        }
    }
    val xOffset = with(DensityAmbient.current) { swipePosition.position.toDp() }
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

enum class SwipeGravity {
    Start, End;

    fun toContentGravity(): Alignment {
        return if (this == Start)
            ContentGravity.CenterStart
        else
            ContentGravity.CenterEnd
    }
}

