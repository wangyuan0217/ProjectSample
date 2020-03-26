package com.trump.library_common.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * @author 王元_Trump
 * @time 2020/03/20 13:38
 * @desc
 */
public class Utils {

    private static final Handler UTIL_HANDLER = new Handler(Looper.getMainLooper());


    public static void runOnUiThread(final Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            Utils.UTIL_HANDLER.post(runnable);
        }
    }

    public static void runOnUiThreadDelayed(final Runnable runnable, long delayMillis) {
        Utils.UTIL_HANDLER.postDelayed(runnable, delayMillis);
    }

}
