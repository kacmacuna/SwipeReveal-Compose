package com.example.swipereveal

import android.animation.ValueAnimator
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.ui.core.Modifier
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.foundation.gestures.draggable

@Composable
internal fun Modifier.swipable(
    position: MutableState<Float>,
    minPx: Float,
    maxPx: Float
): Modifier {
    val avg = (maxPx + minPx) / 2
    return this + draggable(
        dragDirection = DragDirection.Horizontal,
        onDragStopped = {
            ValueAnimator.ofFloat(position.value, if (position.value < avg) minPx else maxPx)
                .apply {
                    addUpdateListener {
                        position.value = it.animatedValue as Float
                    }
                    duration = 333
                    start()
                }
        }
    ) { delta ->
        val old = position.value
        position.value = (position.value + delta).coerceIn(minPx, maxPx)
        position.value - old
    }
}