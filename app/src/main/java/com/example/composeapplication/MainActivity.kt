package com.example.composeapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.*
import androidx.ui.core.*
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.material.*
import androidx.ui.res.colorResource
import androidx.ui.tooling.preview.Preview
import com.example.swipereveal.model.SwipeActionButton
import com.example.swipereveal.model.swipeActionButton
import com.example.swipereveal.sample.DraggableSample


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                RootLayout()
            }
        }
    }
}


@Composable
fun RootLayout() {
    DraggableSample(
        listOf(
            swipeActionButton {
                nameRes = R.string.common_delete
                imageRes = R.drawable.ic_trash
                colorRes = R.color.colorPrimary
                textColorRes = R.color.colorAccent
            }
        )
    )
}


@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        RootLayout()
    }
}