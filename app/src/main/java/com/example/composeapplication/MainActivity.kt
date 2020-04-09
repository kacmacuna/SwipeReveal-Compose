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
import com.example.swipereveal.sample.DraggableSample


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


@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        TestPointerInput()
    }
}