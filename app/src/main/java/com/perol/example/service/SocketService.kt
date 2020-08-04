package com.perol.example.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.net.ServerSocket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.concurrent.thread

class SocketBinder(private val socketService: SocketService) : Binder() {

}

class SocketService : Service() {
    val executorService: ExecutorService = Executors.newCachedThreadPool()
    var needStop = false
    var serverSocket: ServerSocket? = null
    override fun onCreate() {
        thread {
            serverSocket = ServerSocket(8888)
            while (!needStop) {
                val socket = serverSocket!!.accept()
                executorService.submit {
                    try {
                        val inputStream = socket.getInputStream()
                        val byteArray: ByteArray = inputStream.readBytes()
                        val rawStr = String(byteArray)
                        if (rawStr == "OK") {
                            Log.i("socket service", "OK")
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    socket.close()
                }
            }
            serverSocket?.close()
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        needStop = true
        serverSocket?.close()
        return super.onUnbind(intent)
    }

    override fun onBind(intent: Intent): IBinder {
        return SocketBinder(this)
    }
}
