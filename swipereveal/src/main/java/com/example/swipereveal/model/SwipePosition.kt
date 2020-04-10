package com.example.swipereveal.model

import androidx.compose.Model
import androidx.compose.MutableState

@Model
data class SwipePosition(
    var minLeft: Float,
    var maxRight: Float,
    var position: Float
)