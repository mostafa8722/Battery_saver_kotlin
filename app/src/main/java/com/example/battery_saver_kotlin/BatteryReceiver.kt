package com.example.battery_saver_kotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.ImageView
import android.widget.TextView



class  BatteryReceiver : BroadcastReceiver() {

    private lateinit var img_batter_saver :ImageView;
    private lateinit var percent_label :TextView;
    private lateinit var status_label :TextView;
    override fun onReceive(p0: Context?, p1: Intent?) {


        status_label = (p0 as MainActivity).findViewById(R.id.status_label)
        percent_label = (p0 as MainActivity).findViewById(R.id.percent_label)
        img_batter_saver = (p0 as MainActivity).findViewById(R.id.img_view)

       var action : String? = (p1 as Intent).getAction();

        if(action !=null && action.equals(Intent.ACTION_BATTERY_CHANGED)) {

            var  status:Int =  (p1 as Intent).getIntExtra(BatteryManager.EXTRA_STATUS,-1);

            var message : String = "";

            when(status){
                BatteryManager.BATTERY_STATUS_FULL -> message = "Full"
                BatteryManager.BATTERY_STATUS_CHARGING -> message = "Charging"
                BatteryManager.BATTERY_STATUS_DISCHARGING -> message = "DisCharging"
                BatteryManager.BATTERY_STATUS_NOT_CHARGING -> message = "NotCharging"
                else -> message = "UnKnown"
            }
            status_label.setText(message)

            var level : Int  = (p1 as Intent).getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            var scale : Int  = (p1 as Intent).getIntExtra(BatteryManager.EXTRA_SCALE,-1);
            var percentage : Int = level * 100 /scale ;
            percent_label.setText(percentage.toString() + "%");

            if (percentage < 5) img_batter_saver.setImageResource(R.drawable.b0) else if (percentage < 18) img_batter_saver.setImageResource(
                R.drawable.b25
            ) else if (percentage < 55) img_batter_saver.setImageResource(R.drawable.b50) else if (percentage < 80) img_batter_saver.setImageResource(
                R.drawable.b75
            ) else img_batter_saver.setImageResource(R.drawable.b100)
        }

    }

}