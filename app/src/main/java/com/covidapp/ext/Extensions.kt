package com.covidapp.ext

import android.os.Handler
import android.os.Looper

/**
 * @author emre.memis@ovidos.com
 */

object Extensions {
    /**
     * this function works after the time you set has elapsed.
     * @param mills for manage process time
     * @param action runnable function
     */
    fun runOnHandler(mills: Long, action: () -> Unit) {
        Handler(Looper.getMainLooper())
            .postDelayed(action, mills)
    }
}