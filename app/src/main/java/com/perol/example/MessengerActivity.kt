package com.perol.example

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Messenger
import androidx.appcompat.app.AppCompatActivity
import com.perol.example.service.MessengerService
import kotlinx.android.synthetic.main.activity_messenger.*

class MessengerActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_messenger)
        bindService(Intent(this,MessengerService::class.java),serviceConnection, Context.BIND_AUTO_CREATE)
        start.setOnClickListener {
            messenger?.send(MessengerService.sayHello())
        }
    }

    override fun onStop() {
        unbindService(serviceConnection)
        super.onStop()
    }
}