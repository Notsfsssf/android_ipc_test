package com.perol.example.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.IBinder
import android.os.Messenger
import com.perol.example.CReceiver


class BroadCastService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return Messenger(Handler()).binder
    }

    override fun onCreate() {
        val broadcastReceiver = CReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.test.receiver")
        registerReceiver(broadcastReceiver, intentFilter)
    }
}
