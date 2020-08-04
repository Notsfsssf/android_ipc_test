package com.perol.example

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Messenger
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.perol.example.service.BroadCastService

class BroadCastActivity : AppCompatActivity() {
    private var messenger: Messenger? = null
    private var serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            service?.let {
                messenger = Messenger(service)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linearLayout = LinearLayout(this)
        linearLayout.addView(Button(this).apply {
            text = "click"
            setOnClickListener {
                Intent().also { intent ->
                    intent.action = "com.test.receiver"
                    intent.putExtra("data", "Notice me senpai!")
                    sendBroadcast(intent)
                }
            }
        })
        setContentView(linearLayout)
        bindService(
            Intent(this, BroadCastService::class.java),
            serviceConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    override fun onDestroy() {
        unbindService(serviceConnection)
        super.onDestroy()
    }

}