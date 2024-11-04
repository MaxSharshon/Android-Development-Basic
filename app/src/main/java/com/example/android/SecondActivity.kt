package com.example.android

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    var counter: Int = 0
    private lateinit var counterText: TextView
    private val receiver = CounterReceiver()

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        counter = intent.getIntExtra("counter", 0)

        counterText = findViewById(R.id.counterText)
        counterText.text = counter.toString()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(receiver, IntentFilter("com.example.android.COUNTER_UPDATE"),
                RECEIVER_NOT_EXPORTED
            )
        }

        findViewById<Button>(R.id.goToFragmentButton).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, CounterFragment().apply {
                    arguments = Bundle().apply { putInt("counter", counter) }
                })
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    fun updateCounter(newCounter: Int) {
        counter = newCounter
        counterText.text = counter.toString()
    }
}
