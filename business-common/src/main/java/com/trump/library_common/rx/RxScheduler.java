package com.trump.library_common.rx;

import android.content.Context;

import com.trump.library_common.ui.base.dialog.LoadingDialog;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 王元_Trump
 * @time 2020/03/19 14:06
 * @desc
 */
public class RxScheduler {

    /**
     * RxJava io和main线程
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> inIoMain() {
        return new FlowableTransformer<T, T>() {

            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * RxJava io和main线程,是否显示加载框
     *
     * @param context 上下文环境
     * @param isShow  是否显示loading
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> inIoMainLoading(Context context, final boolean isShow) {

        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscription -> {
                    if (isShow) {
                        LoadingDialog.show(context);
                    }
                })
                .doOnTerminate(() -> {
                    if (isShow) {
                        LoadingDialog.close();
                    }
                });
    }


    /**
     * RxJava io和main线程,并显示加载框
     *
     * @param context 上下文环境
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> inIoMainLoading(Context context) {
        return inIoMainLoading(context, true);
    }
}
