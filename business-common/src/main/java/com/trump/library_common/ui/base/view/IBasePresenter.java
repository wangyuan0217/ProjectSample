package com.trump.library_common.ui.base.view;

import android.content.Context;

import io.reactivex.disposables.Disposable;

/**
 * @author 王元_Trump
 * @time 2020/03/19 15:37
 * @desc
 */
public interface IBasePresenter<V> {

    /**
     * 设置上下文环境
     *
     * @param context
     */
    void setContext(Context context);

    /**
     * 获取上下文环境
     *
     * @return
     */
    Context getContext();

    /**
     * 关联view
     *
     * @param mvpView 业务回调view
     */
    void attachView(V mvpView);

    /**
     * 销毁view
     */
    void detachView();

    /**
     * 添加任务
     *
     * @param disposable 观察对象
     */
    void addTask(Disposable disposable);
}