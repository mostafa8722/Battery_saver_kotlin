package com.example.battery_saver_kotlin

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private var batteryReceiver : BatteryReceiver = BatteryReceiver();
    private var intentFilter : IntentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(batteryReceiver,intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(batteryReceiver)
    }
}