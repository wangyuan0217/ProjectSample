package com.trump.library_common.http.rxJava;


import com.trump.library_common.config.HttpResponseCode;
import com.trump.library_common.http.response.BaseResponse;
import com.trump.library_common.utils.ToastUtil;

import androidx.annotation.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Description: 统一订阅封装
 */
public abstract class RxConsumer<T> implements Consumer<BaseResponse<T>> {

    @Override
    public void accept(@NonNull BaseResponse<T> baseResponse) throws Exception {
        //正常
        if (HttpResponseCode.RESPONSE_OK.getCode() == baseResponse.getCode()) {
            _accept(baseResponse.getData());
        }
        //token异常
        else if (HttpResponseCode.TOKEN_EXCEPTION.getCode() == baseResponse.getCode()) {
            _accept(baseResponse.getData());
        }
        //其他情况
        else {
            _handleMsg(baseResponse.getCode(), baseResponse.getMsg(), baseResponse.getData());
        }
//        //密码异常
//        else if (HttpResponseConstant.TOKEN_EXCEPTION.equals(baseResponse.getSub_code())) {
//            _handleMsg(baseResponse.getMessage(), baseResponse.getSub_code(), baseResponse.getSub_message());
//            EventBus.getDefault().post(new LoginOutEvent(false));
//        }
//        //token异常
//        else if (HttpResponseConstant.PASSWORD_EXCEPTION.equals(baseResponse.getSub_code())) {
//            _handleMsg(baseResponse.getMessage(), baseResponse.getSub_code(), baseResponse.getSub_message());
//            EventBus.getDefault().post(new LoginOutEvent(false));
//        } else {
//            _handleMsg(baseResponse.getMessage(), baseResponse.getSub_code(), baseResponse.getSub_message());
//        }
    }

    public abstract void _accept(T t);

    public void _handleMsg(int code, String message, T t) {
        // TODO: 2020/3/26 0026
        ToastUtil.show(message);
    }
}
