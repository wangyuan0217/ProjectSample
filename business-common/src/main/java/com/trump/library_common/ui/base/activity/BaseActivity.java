package com.trump.library_common.ui.base.activity;

import android.content.Context;
import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.trump.library_common.R;
import com.trump.library_common.ui.base.dialog.LoadingDialog;
import com.trump.library_common.ui.base.view.IBasePresenter;
import com.trump.library_common.ui.base.view.IBaseView;
import com.trump.library_common.utils.KeyBoardUtil;
import com.trump.library_common.utils.LanguageUtil;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 王元_Trump
 * @time 2020/03/19 15:40
 * @desc
 */
public abstract class BaseActivity<V extends IBaseView, P extends IBasePresenter<V>> extends AppCompatActivity {

    /**
     * 当前类实例
     */
    public BaseActivity instance;

    /**
     * Presenter
     */
    private P presenter;

    /**
     * 沉浸式状态栏
     */
    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //防止屏幕休眠
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }

        instance = this;

        //是否全屏
        if (applyFullScreen()) {
            setFullScreenModel();
        }
        //是否设置沉浸式状态栏
        else if (applyImmersionBar()) {
            setImmersionBar(getStatusBarColor());
        }

        presenter = initPresenter();
        if (presenter != null) {
            presenter.setContext(this);
            presenter.attachView((V) this);
        }

        if (applyEventBus()) {
            EventBus.getDefault().register(this);
        }

        init(savedInstanceState);
        initViews(savedInstanceState);
        initToolbar(savedInstanceState);
        initListener();
        initData();
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

        if (presenter != null) {
            presenter.detachView();
        }

//        if (applyImmersionBar() || applyFullScreen()) {
//            if (immersionBar != null) immersionBar.destroy();
//        }

        if (applyEventBus()) {
            EventBus.getDefault().unregister(this);
        }

        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
    }

    /**
     * 获取当前类实例
     *
     * @return
     */
    public BaseActivity getInstance() {
        return instance;
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
        return R.color.colorPrimary;
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
        immersionBar = ImmersionBar.with(this);
        immersionBar.statusBarColor(statusBarColor)
                .keyboardEnable(keyboardEnable)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    /**
     * 全屏App内容填充状态栏
     */
    protected void setFullScreenModel() {
        immersionBar = ImmersionBar.with(this);
        immersionBar.keyboardEnable(false)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    /**
     * 解决软键盘与沉浸式状态冲突
     */
    protected void setImmersionBarKeyboardEnable() {
        if (immersionBar != null) {
            immersionBar.keyboardEnable(true)
                    .init();
        }
    }

    /**
     * 布局layout id
     *
     * @return layout id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化Presenter
     *
     * @return P
     */
    protected abstract P initPresenter();

    /**
     * 初始化
     */
    protected void init(Bundle savedInstanceState) {
    }

    /**
     * 初始化toolBar
     */
    protected void initToolbar(Bundle savedInstanceState) {
    }

    /**
     * 初始化view
     */
    protected void initViews(Bundle savedInstanceState) {
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

    //多语言实现
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageUtil.attachBaseContext(newBase));
    }

    /**
     * 获取Presenter
     *
     * @return P
     */
    protected P getPresenter() {
        return presenter;
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

}
