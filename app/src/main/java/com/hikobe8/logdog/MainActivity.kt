package com.hikobe8.logdog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        i()
        e()
    }

    private fun i(){
        Log.i("hikobe8", "i")
        Test.i("hikobe8", "i")
    }

    private fun e(){
        Log.e("hikobe8", "e")
    }

}