package com.trump.library_common.http.rxJava;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.socks.library.KLog;
import com.trump.library_common.config.HttpResponseCode;
import com.trump.library_common.app.BaseApplication;
import com.trump.library_common.ui.base.dialog.LoadingDialog;
import com.trump.library_common.utils.NetworkUtil;
import com.trump.library_common.utils.ToastUtil;

import org.json.JSONException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Description: 统一异常封装
 */
public class RxThrowableConsumer implements Consumer<Throwable> {
    @Override
    public void accept(@NonNull Throwable throwable) {
        KLog.e("http", "error = " + throwable);
        throwable.printStackTrace();
        //网络
        if (!NetworkUtil.isNetConnected(BaseApplication.getContext())) {
            _accept(HttpResponseCode.NET_ERROR.getCode(), HttpResponseCode.NET_ERROR.getMessage());
        }
        //解析异常
        else if (throwable instanceof JsonParseException || throwable instanceof JSONException || throwable instanceof ParseException) {
            _accept(HttpResponseCode.PARSE_ERROR.getCode(), HttpResponseCode.PARSE_ERROR.getMessage());
        }
        //其它
        else {
            _accept(HttpResponseCode.UNKNOWN.getCode(), throwable.getMessage());
        }
    }

    private void _accept(int errorCode, String errorMsg) {
        KLog.e("http", "errorMsg = " + errorMsg);
        ToastUtil.show(errorMsg);
        LoadingDialog.close();
    }

}
