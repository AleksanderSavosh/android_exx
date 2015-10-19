package com.savosh.exx.util;

import android.util.Log;

public class LogUtil {

    public static void toLog(String tag, String text){
        toLog(tag, text, null);
    }

    public static void toLog(String tag, String text, Exception e){
        boolean hasErrorText = e != null && e.getMessage() != null;
        Log.d(tag, hasErrorText ? e.getMessage() : text, e);
        if(e != null) {
            Log.e(tag, hasErrorText ? e.getMessage() : text);
        }
    }

}
