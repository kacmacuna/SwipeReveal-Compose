package com.example.swipereveal

import android.animation.ValueAnimator
import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.DensityAmbient
import androidx.ui.core.LayoutCoordinates
import androidx.ui.core.Modifier
import androidx.ui.core.onPositioned
import androidx.ui.foundation.*
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.foundation.gestures.draggable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.ImageAsset
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
import androidx.ui.unit.times
import androidx.ui.unit.toPx
import com.example.swipereveal.sample.DraggableSample


@Composable
fun SwipeReveal(
    layoutHeight: Dp,
    swipeActionButton: Collection<SwipeActionButton>,
    children: @Composable() () -> Unit
) {
    var position by state { 0f }
    val max = 0.dp

    var minPx by state { 0F }
    val maxPx = with(DensityAmbient.current) { max.toPx().value }

    val avg = (maxPx + minPx) / 2
    Box(
        gravity = ContentGravity.CenterEnd,
        modifier = draggable(
            dragDirection = DragDirection.Horizontal,
            onDragStopped = {
                ValueAnimator.ofFloat(position, if (position < avg) minPx else maxPx).apply {
                    addUpdateListener {
                        position = it.animatedValue as Float
                    }
                    duration = 333
                    start()
                }
            }
        ) { delta ->
            val old = position
            position = (position + delta).coerceIn(minPx, maxPx)
            position - old
        }.preferredHeight(layoutHeight).fillMaxHeight()
    ) {
        SetActionButtons(swipeActionButton) {
            minPx = -it.size.width.toPx().value
        }
        val xOffset = with(DensityAmbient.current) { position.toDp() }
        Box(Modifier.offset(x = xOffset, y = 0.dp).fillMaxWidth().wrapContentHeight()) {
            children()
        }
    }

}

@Composable
private fun SetActionButtons(
    swipeActionButton: Collection<SwipeActionButton>,
    onPositioned: (LayoutCoordinates) -> Unit
) {
    Row(
        arrangement = Arrangement.End,
        modifier = Modifier
            .preferredHeightIn()
            .fillMaxHeight()
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
                    if (it.drawableRes != null)
                        loadVectorResource(id = it.drawableRes).resource.resource?.let { va ->
                            Image(
                                asset = va,
                                modifier = Modifier
                                    .preferredSize(24.dp, 24.dp).gravity(ColumnAlign.Center),
                                scaleFit = ScaleFit.FillMinDimension
                            )
                        }
                    Text(
                        text = it.actionName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(textAlign = TextAlign.Center),
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

data class SwipeActionButton(
    val actionName: String,
    val backgroundColor: Color,
    @DrawableRes val drawableRes: Int? = null,
    val onClick: () -> Unit
)