package com.example.swipereveal

import android.animation.ValueAnimator
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.ui.core.Modifier
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.foundation.gestures.draggable
import com.example.swipereveal.model.SwipePosition

@Composable
internal fun Modifier.swipable(
    swipePosition: SwipePosition
): Modifier {
    val avg = (swipePosition.maxRight + swipePosition.minLeft) / 2
    return this + draggable(
        dragDirection = DragDirection.Horizontal,
        onDragStopped = {
            ValueAnimator.ofFloat(swipePosition.position, if (swipePosition.position < avg) swipePosition.minLeft else swipePosition.maxRight)
                .apply {
                    addUpdateListener {
                        swipePosition.position = it.animatedValue as Float
                    }
                    duration = 333
                    start()
                }
        }
    ) { delta ->
        val old = swipePosition.position
        swipePosition.position = (swipePosition.position + delta).coerceIn(swipePosition.minLeft, swipePosition.maxRight)
        swipePosition.position - old
    }
}