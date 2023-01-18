package com.hikobe8.logdog.lib

import android.util.Log

object LogDog {

    private const val TAG = "LogDog"

    fun i(tag:String, msg:String){
        println(TAG + msg)
        Log.i(tag, msg)
    }

    fun d(tag:String, msg:String){
        println(TAG +msg)
        Log.d(tag, msg)
    }

    fun w(tag:String, msg:String){
        println(TAG +msg)
        Log.w(tag, msg)
    }

    fun e(tag:String, msg:String){
        println(TAG +msg)
        Log.e(tag, msg)
    }

}