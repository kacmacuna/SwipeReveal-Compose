package com.example.swipereveal.sample

import android.widget.Toast
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
import com.example.swipereveal.R
import com.example.swipereveal.SwipeActionButton
import com.example.swipereveal.SwipeReveal

@Composable
fun DraggableSample() {
    val squareHeight = 100.dp
    val current = ContextAmbient.current
    SwipeReveal(squareHeight, MOCK.map {
        it.copy(onClick = {
            Toast.makeText(current, it.actionName, Toast.LENGTH_SHORT).show()
        })
    }) {
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

internal val MOCK =
    listOf(
        SwipeActionButton(
            "Delete",
            Color.Magenta
        ) {},
        SwipeActionButton(
            "Edit",
            Color.Blue
        ) {}
    )