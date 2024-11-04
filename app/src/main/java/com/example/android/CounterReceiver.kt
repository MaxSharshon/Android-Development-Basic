package com.example.android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CounterReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val counter = intent.getIntExtra("counter", 0)
        Toast.makeText(context, "Counter: $counter", Toast.LENGTH_SHORT).show()
        if (context is MainActivity) {
            context.updateCounter(counter)
        } else if (context is SecondActivity) {
            context.updateCounter(counter)
        }
    }
}
