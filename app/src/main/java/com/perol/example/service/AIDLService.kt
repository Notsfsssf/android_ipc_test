package com.perol.example.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.perol.example.IMyAidlInterface

class AIDLService : Service() {
    private val binder = object : IMyAidlInterface.Stub() {
        override fun basicTypes(anInt: Int, rect: Rect?) {
            if (anInt == 1) {
               Log.d("aidl",rect!!.left.toString())//在Logcat :AIDL进程可以看到
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}
