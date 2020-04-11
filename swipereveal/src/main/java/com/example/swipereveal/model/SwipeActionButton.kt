package com.example.swipereveal.model

import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.ContentGravity
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.VectorAsset


data class SwipeActionButton(
    val actionName: String,
    val backgroundColor: Color = Color.White,
    val vector: VectorAsset? = null,
    val textColor: Color = Color.Black,
    val onClick: () -> Unit,
    val gravity: SwipeGravity = SwipeGravity.End
)

enum class SwipeGravity {
    Start, End;

    fun toContentGravity(): Alignment {
        return if (this == Start)
            ContentGravity.CenterStart
        else
            ContentGravity.CenterEnd
    }
}

