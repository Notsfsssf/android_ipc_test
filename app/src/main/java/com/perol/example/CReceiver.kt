package com.perol.example

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class CReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val data = it.getStringExtra("data")
            Log.i("data", data)
        }
    }
}