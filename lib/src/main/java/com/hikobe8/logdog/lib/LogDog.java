package com.hikobe8.logdog.lib;

import android.util.Log;

public class LogDog {

    private static final String TAG = "LogDog-----";

    public static int i(String tag, String msg){
        System.out.println(TAG + msg);
        return Log.i(tag, msg);
    }

    public static int d(String tag, String msg){
        System.out.println(TAG +msg);
        return Log.d(tag, msg);
    }

    public static int w(String tag, String msg){
        System.out.println(TAG +msg);
        return Log.w(tag, msg);
    }

    public static int e(String tag, String msg){
        System.out.println(TAG +msg);
        return Log.e(tag, msg);
    }
}
