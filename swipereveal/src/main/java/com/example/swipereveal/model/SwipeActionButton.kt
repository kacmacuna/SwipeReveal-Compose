package com.example.swipereveal.model

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.Composable
import androidx.ui.graphics.Color
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.vector.VectorAsset


data class SwipeActionButton(
    val actionName: String,
    val backgroundColor: Color = Color.White,
    val vector: VectorAsset? = null,
    val textColor: Color = Color.Black,
    val onClick: () -> Unit
)