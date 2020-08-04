package com.perol.example

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.perol.example.service.SocketService
import kotlinx.android.synthetic.main.activity_socket.*
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.concurrent.thread

class SocketActivity : AppCompatActivity() {
    private var serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            service?.let {
            }
        }

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket)
        bindService(
            Intent(this, SocketService::class.java),
            serviceConnection,
            Context.BIND_AUTO_CREATE
        )
        click.setOnClickListener {
            thread {
                val socket = Socket()
                socket.connect(InetSocketAddress(8888))
                socket.getOutputStream().write("OK".toByteArray())
                socket.shutdownOutput()
                socket.close()

            }
        }
    }

    override fun onDestroy() {
        unbindService(serviceConnection)
        super.onDestroy()
    }
}