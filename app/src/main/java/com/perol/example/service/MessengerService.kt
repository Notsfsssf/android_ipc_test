package com.perol.example.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.widget.Toast

class MessengerService : Service() {
    companion object {
        fun sayHello(): Message {
            return Message.obtain().also {
                it.what = 1
                it.data= Bundle().also {
                    it.putString("a","a")
                }
            }

        }
    }

    lateinit var messenger: Messenger

    internal class ServiceHandler(private val context: Context) : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> Toast.makeText(context, msg.data.getString("a","1"), Toast.LENGTH_SHORT).show()
                else -> super.handleMessage(msg)
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        messenger = Messenger(ServiceHandler(applicationContext))
        return messenger.binder
    }
}
