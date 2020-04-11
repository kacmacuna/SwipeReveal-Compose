package com.example.swipereveal

import android.animation.ValueAnimator
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.foundation.gestures.draggable
import com.example.swipereveal.model.SwipePosition
import kotlin.math.absoluteValue

@Composable
internal fun Modifier.swipable(
    swipePosition: SwipePosition
): Modifier {
    val avgMinLeft = swipePosition.minLeft / 2
    val avgMaxRight = swipePosition.maxRight / 2
    return this + draggable(
        dragDirection = DragDirection.Horizontal,
        onDragStopped = {
            if (swipePosition.position > 0) {
                ValueAnimator.ofFloat(
                    swipePosition.position,
                    if (swipePosition.position < avgMaxRight) 0f else swipePosition.maxRight
                ).animateDragStopped(swipePosition)
            } else {
                ValueAnimator.ofFloat(
                    swipePosition.position,
                    if (swipePosition.position < avgMinLeft) swipePosition.minLeft else 0f
                ).animateDragStopped(swipePosition)
            }
        }
    ) { delta ->
        val old = swipePosition.position
        swipePosition.position =
            (swipePosition.position + delta).coerceIn(swipePosition.minLeft, swipePosition.maxRight)
        swipePosition.position - old
    }
}

private fun ValueAnimator.animateDragStopped(swipePosition: SwipePosition) {
    addUpdateListener {
        swipePosition.position = it.animatedValue as Float
    }
    duration = 333
    start()
}