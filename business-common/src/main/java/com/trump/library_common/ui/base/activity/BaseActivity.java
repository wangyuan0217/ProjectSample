package com.trump.library_common.ui.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.gyf.immersionbar.ImmersionBar;
import com.trump.library_common.R;
import com.trump.library_common.ui.base.dialog.LoadingDialog;
import com.trump.library_common.ui.base.view.IBasePresenter;
import com.trump.library_common.ui.base.view.IBaseView;
import com.trump.library_common.utils.KeyBoardUtil;
import com.trump.library_common.utils.LanguageUtil;
import com.trump.library_common.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * @author 王元_Trump
 * @time 2020/03/19 15:40
 * @desc
 */
public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {

    private ViewBinding mBinding;

    /**
     * 当前类实例
     */
    protected BaseActivity mActivity;
    protected Context mContext;

    /**
     * 沉浸式状态栏
     */
    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            Class cls = (Class) type.getActualTypeArguments()[0];
            Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class);
            mBinding = (VB) inflate.invoke(null, getLayoutInflater());
            setContentView(mBinding.getRoot());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mContext = this;
        mActivity = this;

        //是否全屏
        if (applyFullScreen()) {
            setFullScreenModel();
        }
        //是否设置沉浸式状态栏
        else if (applyImmersionBar()) {
            setImmersionBar(getStatusBarColor());
        }

        if (applyEventBus()) {
            EventBus.getDefault().register(this);
        }

        init(savedInstanceState);
        initViews(savedInstanceState);
        initData();
        initListener();
    }

    public VB getBinding() {
        return (VB) mBinding;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        KeyBoardUtil.hideSoftInputFromWindow(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (applyEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    /**
     * 获取当前类实例
     *
     * @return
     */
    public BaseActivity getActivity() {
        return mActivity;
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
     * 是否设置沉浸式状态栏
     *
     * @return
     */
    protected boolean applyImmersionBar() {
        return true;
    }

    /**
     * 是否设置全屏显示
     *
     * @return
     */
    protected boolean applyFullScreen() {
        return false;
    }

    /**
     * 系统StatusBar颜色
     *
     * @return
     */
    protected int getStatusBarColor() {
        return R.color.white;
    }

    /**
     * 设置系统statusBar颜色
     *
     * @param statusBarColor 状态栏颜色
     */
    protected void setImmersionBar(int statusBarColor) {
        setImmersionBar(statusBarColor, false);
    }

    /**
     * 设置系统statusBar颜色
     *
     * @param statusBarColor 状态栏颜色
     */
    protected void setImmersionBar(int statusBarColor, boolean keyboardEnable) {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(statusBarColor)
                .keyboardEnable(keyboardEnable)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    /**
     * 全屏App内容填充状态栏
     */
    protected void setFullScreenModel() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(false)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    /**
     * 解决软键盘与沉浸式状态冲突
     */
    protected void setImmersionBarKeyboardEnable() {
        if (mImmersionBar != null) {
            mImmersionBar.keyboardEnable(true)
                    .init();
        }
    }

    /**
     * 初始化
     */
    protected void init(Bundle savedInstanceState) {
    }

    /**
     * 初始化view
     */
    protected void initViews(Bundle savedInstanceState) {
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听器
     */
    protected void initListener() {
    }

    //多语言实现
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageUtil.attachBaseContext(newBase));
    }

    /**
     * 显示进度条
     * notice：基类中实现IBaseView的回调
     */
    public void showProgress() {
        LoadingDialog.show(this);
    }

    /**
     * 隐藏进度条
     * notice：基类中实现IBaseView的回调
     */
    public void hideProgress() {
        LoadingDialog.close();
    }

    public void showToast(String message) {
        ToastUtil.show(mActivity, message);
    }
}
