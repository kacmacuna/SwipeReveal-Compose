package com.example.composeapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.*
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBackground
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.layout.ColumnScope.gravity
import androidx.ui.material.*
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.*
import com.example.swipereveal.SwipeActionButton
import com.example.swipereveal.SwipeReveal

val MOCK =
    listOf(
        SwipeActionButton(
            "Delete",
            Color.Magenta,
            R.drawable.ic_trash
        ) {},
        SwipeActionButton(
            "Edit",
            Color.Blue,
            R.drawable.ic_edit
        ) {}
    )


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier.gravity(ColumnAlign.Center),
                    arrangement = Arrangement.Top
                ) {
                    TestPointerInput()
                }
            }
        }
    }
}


@Composable
fun TestPointerInput() {
    DraggableSample()
}

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


@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        TestPointerInput()
    }
}