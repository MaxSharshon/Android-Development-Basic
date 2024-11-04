package com.example.android

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class CounterFragment : Fragment() {
    private var counter: Int = 0
    private lateinit var counterText: TextView
    private val receiver = CounterReceiver()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_counter, container, false)
        counter = arguments?.getInt("counter", 0) ?: 0
        counterText = view.findViewById(R.id.counterText)
        counterText.text = counter.toString()

        view.findViewById<Button>(R.id.counterButton).setOnClickListener {
            counter++
            counterText.text = counter.toString()
            val serviceIntent = Intent(activity, CounterService::class.java)
            serviceIntent.putExtra("counter", counter)
            activity?.startService(serviceIntent)
        }

        val filter = IntentFilter("com.example.android.COUNTER_UPDATE")
        filter.addCategory(Intent.CATEGORY_DEFAULT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                activity?.registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.unregisterReceiver(receiver)
    }

    override fun onPause() {
        super.onPause()
        (activity as? SecondActivity)?.counter = counter
    }
}
