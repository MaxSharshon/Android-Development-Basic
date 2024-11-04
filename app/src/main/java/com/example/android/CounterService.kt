package com.example.android

import android.app.Service
import android.content.Intent
import android.os.IBinder

class CounterService : Service() {
    private var counter: Int = 0

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        counter = intent?.getIntExtra("counter", 0) ?: 0
        sendCounterUpdate()
        return START_STICKY
    }

    private fun sendCounterUpdate() {
        val broadcastIntent = Intent("com.example.android.COUNTER_UPDATE")
        broadcastIntent.putExtra("counter", counter)
        sendBroadcast(broadcastIntent)
    }
}
