package com.trump.library_common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.trump.library_common.app.BaseApplication;


public class ToastUtil {

    private static Toast toast;

    public static void show(String msg) {
        show(BaseApplication.getContext(), msg);
    }

    /**
     * 显示Toast消息
     *
     * @param msg 消息
     */
    public static void show(Context context, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 显示Toast消息
     *
     * @param resId 消息资源id
     */
    public static void show(Context context, int resId) {
        show(context, context.getString(resId));
    }

}
