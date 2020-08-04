package com.perol.example

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Rect
import android.os.Bundle
import android.os.IBinder
import android.os.Messenger
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.perol.example.service.AIDLService

class AIDLActivity : AppCompatActivity() {
    private var iMyAidlInterface: IMyAidlInterface? = null
    private var serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            service?.let {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(it)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_i_d_l)
        setSupportActionBar(findViewById(R.id.toolbar))
        bindService(
            Intent(this, AIDLService::class.java),
            serviceConnection,
            Context.BIND_AUTO_CREATE
        )
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            iMyAidlInterface?.basicTypes(1, Rect())
        }
    }

    override fun onStop() {
        unbindService(serviceConnection)
        super.onStop()
    }
}