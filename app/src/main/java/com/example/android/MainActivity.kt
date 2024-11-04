package com.example.android

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var counter: Int = 0
    private lateinit var counterText: TextView

    private val receiver = CounterReceiver()

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counterText = findViewById(R.id.counterText)
        findViewById<Button>(R.id.counterButton).setOnClickListener {
            counter++
            counterText.text = counter.toString()
            val serviceIntent = Intent(this, CounterService::class.java)
            serviceIntent.putExtra("counter", counter)
            startService(serviceIntent)
        }

        val filter = IntentFilter("com.example.android.COUNTER_UPDATE")
        filter.addCategory(Intent.CATEGORY_DEFAULT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)
        }

        findViewById<Button>(R.id.goToSecondActivityButton).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("counter", counter)
            startActivity(intent)
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
