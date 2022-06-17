package com.trump.library_common.ui.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.trump.library_common.ui.base.activity.BaseActivity;
import com.trump.library_common.ui.base.dialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author 王元_Trump
 * @time 2020/03/19 15:39
 * @desc
 */
public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {

    private ViewBinding mBinding;
    /**
     * 基类Activity
     */
    protected BaseActivity mActivity, mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
        mContext = mActivity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            Class cls = (Class) type.getActualTypeArguments()[0];
            Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class);
            mBinding = (VB) inflate.invoke(null, getLayoutInflater());
            return mBinding.getRoot();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public VB getBinding() {
        return (VB) mBinding;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        handleImmersionBar();

        init(savedInstanceState);
        initViews(view, savedInstanceState);
        initData();
        initListener();

        if (applyEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {

//        if (!applyLazyFragmentHandleImmersionBar()) {
//            if (applyImmersionBar() || applyFullScreen()) {
//                if (immersionBar != null) {
//                    immersionBar.destroy();
//                }
//            }
//        }

        if (applyEventBus()) {
            EventBus.getDefault().unregister(this);
        }

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        freeDisposable();
        super.onDetach();
    }

    /**
     * 配置状态栏
     */
    protected void handleImmersionBar() {

    }

    /**
     * 初始化view
     */
    protected void initViews(View view, Bundle savedInstanceState) {
    }

    /**
     * 初始化
     */
    protected void init(Bundle savedInstanceState) {
    }


    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 设置监听器
     */
    protected void initListener() {
    }

    /**
     * 是否使用EventBus
     *
     * @return
     */
    protected boolean applyEventBus() {
        return false;
    }

    /**
     * 显示进度条
     * notice：基类中实现IBaseView的回调
     */
    public void showProgress() {
        LoadingDialog.show(mActivity);
    }

    /**
     * 隐藏进度条
     * notice：基类中实现IBaseView的回调
     */
    public void hideProgress() {
        LoadingDialog.close();
    }

    private CompositeDisposable compositeDisposable;

    public void addDisposable(Disposable disposable) {
        if (disposable == null) return;

        if (compositeDisposable == null) {
            this.compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void freeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

}