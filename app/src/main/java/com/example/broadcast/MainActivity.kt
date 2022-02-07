package com.example.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val TAG:String = MainActivity::class.java.simpleName
    var txt: TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt=findViewById(R.id.txt)
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batttterBroadcastReceiver,intentFilter)
    }

    private val batttterBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            if(intent?.action=="android.intent.action.BATTERY_CHANGED"){
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1)
                Log.d(TAG,"onreceive: Battery level")
                txt?.post {
                    txt?.text=level.toString().plus("").plus("%")
                }
            }
        }
    }
    override fun onDestroy() {
        unregisterReceiver(batttterBroadcastReceiver)
        super.onDestroy()
    }

}