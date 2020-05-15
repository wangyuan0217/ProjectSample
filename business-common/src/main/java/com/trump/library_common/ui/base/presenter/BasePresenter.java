package com.trump.library_common.ui.base.presenter;

import android.content.Context;

import com.trump.library_common.ui.base.view.IBasePresenter;
import com.trump.library_common.ui.base.view.IBaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author 王元_Trump
 * @time 2020/03/19 15:38
 * @desc
 */
public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    /**
     * context
     */
    private Context context;

    /**
     * 回调接口
     */
    private V mvpView;

    /**
     * 统一管理类
     */
    private CompositeDisposable compositeDisposable;

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        mvpView = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

    @Override
    public void addTask(Disposable disposable) {
        if (disposable == null) return;
        
        if (compositeDisposable == null) {
            this.compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public V getMvpView() {
        return mvpView;
    }

}