package com.example.swipereveal.model

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.Composable
import androidx.core.content.ContextCompat
import androidx.ui.foundation.contentColor
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.res.colorResource
import androidx.ui.res.loadVectorResource
import androidx.ui.res.stringResource


class SwipeActionButtonBuilder
internal constructor() {
    var name: String = ""

    @StringRes
    var nameRes: Int? = null

    var image: VectorAsset? = null

    @DrawableRes
    var imageRes: Int? = null

    var color: Color = Color.White

    @ColorRes
    var colorRes: Int? = null

    var textColor: Color = Color.Black

    @ColorRes
    var textColorRes: Int? = null

    var onClick = {}

    @Composable
    fun build(): SwipeActionButton {
        return SwipeActionButton(
            getActionName(),
            getBackgroundColor(),
            getVectorAsset(),
            getActionTextColor(),
            onClick
        )
    }

    @Composable
    private fun getVectorAsset(): VectorAsset? {
        return if (imageRes != null)
            loadVectorResource(id = imageRes!!).resource.resource
        else
            return image
    }

    @Composable
    private fun getActionName(): String {
        return if (nameRes != null)
            stringResource(id = nameRes!!)
        else
            name
    }

    @Composable
    private fun getBackgroundColor(): Color {
        return if (colorRes != null)
            colorResource(id = colorRes!!)
        else
            color
    }

    @Composable
    private fun getActionTextColor(): Color {
        return if (textColorRes != null)
            colorResource(id = textColorRes!!)
        else
            textColor
    }
}

@Composable
fun swipeActionButton(block: SwipeActionButtonBuilder.() -> Unit) =
    SwipeActionButtonBuilder().apply(block).build()