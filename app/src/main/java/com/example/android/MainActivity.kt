package com.example.android

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var counter: Int = 0
    private lateinit var counterText: TextView
    private val receiver = CounterReceiver()
    private val numbers = intArrayOf(5, 3, 8, 4, 2, 7, 1, 10, 6, 9)

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

        findViewById<Button>(R.id.sortButton).setOnClickListener {
            val startTime = System.currentTimeMillis()

            // Start sorting in a coroutine (background thread)
            Thread {
                bubbleSort(numbers)
                val endTime = System.currentTimeMillis()
                val duration = endTime - startTime

                // Show sorting duration in a Toast on the main thread
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(this@MainActivity, "Sort duration: $duration ms", Toast.LENGTH_SHORT).show() }
            }.start()
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

    private fun bubbleSort(array: IntArray) {
        val n = array.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (array[j] > array[j + 1]) {
                    val temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp
                }
            }
        }
    }
}
