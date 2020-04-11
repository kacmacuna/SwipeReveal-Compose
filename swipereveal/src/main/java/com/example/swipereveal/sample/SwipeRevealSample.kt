package com.example.swipereveal.sample

import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBackground
import androidx.ui.graphics.Color
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.preferredHeight
import androidx.ui.text.TextStyle
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.swipereveal.SwipeReveal
import com.example.swipereveal.model.SwipeActionButton
import com.example.swipereveal.model.SwipeGravity
import com.example.swipereveal.model.swipeActionButton

@Composable
fun DraggableSample(swipeActionButtons: List<SwipeActionButton> = getMockButtons()) {
    val squareHeight = 100.dp
    SwipeReveal(squareHeight, swipeActionButtons) {
        Box(
            gravity = ContentGravity.Center,
            modifier = Modifier
                .preferredHeight(squareHeight)
                .fillMaxWidth()
                .drawBackground(Color.White)
        ) {
            Text(
                text = "Cool service but not as cool as me",
                style = TextStyle(color = Color.Black, fontSize = 18.sp)
            )
        }
    }
}


@Composable
fun getMockButtons() =
    listOf(
        swipeActionButton {
            name = "Delete"
            color = Color.Magenta
        },
        swipeActionButton {
            name = "Edit"
            color = Color.Blue
        },
        swipeActionButton {
            name = "Remind"
            color = Color.Yellow
            gravity = SwipeGravity.Start
        }
    )