package com.example.lvshiqi.sort;

import android.util.Log;

/**
 * Created by lvshiqi on 16-11-28.
 * To formate log
 */
public class LogUtils {
    public static void e (String tag , String info, Exception e){
        Log.e(tag, info, e);
    }

    public static void i (String tag , String info, Exception e){
        Log.i(tag, info, e);
    }

    public static void d (String tag , String info, Exception e){
        Log.d(tag, info, e);
    }
}
