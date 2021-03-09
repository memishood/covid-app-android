package com.covidapp.ext

import android.os.Handler
import android.os.Looper

object Extensions {
    fun runOnHandler(mills: Long, action: () -> Unit) {
        Handler(Looper.getMainLooper())
            .postDelayed(action, mills)
    }
}