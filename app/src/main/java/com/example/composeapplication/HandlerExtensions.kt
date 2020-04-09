package com.example.composeapplication

import android.os.Handler
import android.os.Looper

infix fun (() -> Any).waitFor(delayMillis: Long) {
    Looper.myLooper()?.let {
        Handler(it).postDelayed({
            this()
        }, 1000)
    }
}