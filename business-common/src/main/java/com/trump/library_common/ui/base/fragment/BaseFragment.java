package com.trump.library_common.ui.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.trump.library_common.ui.base.activity.BaseActivity;
import com.trump.library_common.ui.base.dialog.LoadingDialog;
import com.trump.library_common.ui.base.view.IBasePresenter;
import com.trump.library_common.ui.base.view.IBaseView;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 王元_Trump
 * @time 2020/03/19 15:39
 * @desc
 */
public abstract class BaseFragment<V extends IBaseView, P extends IBasePresenter<V>> extends Fragment {

    private Unbinder mUnbinder;
    /**
     * Presenter
     */
    private P presenter;

    /**
     * 基类Activity
     */
    private BaseActivity activity;

    /**
     * 沉浸式状态栏
     */
    private ImmersionBar immersionBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        activity = (BaseActivity) context;

        presenter = initPresenter();
        if (presenter != null) {
            presenter.setContext(activity);
            presenter.attachView((V) this);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);

//        if (view.getParent() != null) {
//            ViewGroup parent = (ViewGroup) view.getParent();
//            parent.removeView(view);
//        }
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(savedInstanceState);
        initToolbar(savedInstanceState);
        initViews(view, savedInstanceState);
        initListener();
        initData();

        if (!applyLazyFragmentHandleImmersionBar()) {
            if (applyFullScreen()) {
                setFullScreenModel();
            } else if (applyImmersionBar()) {
                setImmersionBar();
            }
        }

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

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {

        if (presenter != null) {
            presenter.detachView();
        }

        super.onDetach();
    }

    /**
     * 初始化Presenter
     *
     * @return PP
     */
    protected abstract P initPresenter();

    /**
     * 布局layout id
     *
     * @return layout id
     */
    protected abstract int getLayoutId();

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
     * 初始化toobar
     */
    protected void initToolbar(Bundle savedInstanceState) {
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
     * 全屏App内容填充状态栏
     */
    protected void setFullScreenModel() {
        immersionBar = ImmersionBar.with(this);
        immersionBar.statusBarDarkFont(true, 0.2f).keyboardEnable(true).init();
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
     * 初始化沉浸式
     */
    public void setImmersionBar() {
        try {
            immersionBar = ImmersionBar.with(this);
            immersionBar.fitsSystemWindows(getFitsSystemWindows()).keyboardEnable(true).statusBarDarkFont(true, 0.2f).init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否铺满状态栏区域
     *
     * @return false 重叠铺满，true：下方view与状态栏不重叠
     */
    protected boolean getFitsSystemWindows() {
        return true;
    }

    /**
     * 是否由LazyFragment处理沉浸式状态栏
     *
     * @return
     */
    protected boolean applyLazyFragmentHandleImmersionBar() {
        return false;
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
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean applyImmersionBar() {
        return true;
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
     * 获取Presenter
     *
     * @return
     */
    protected P getPresenter() {
        return presenter;
    }

    /**
     * 获取宿主Activity
     *
     * @return activity
     */
    protected BaseActivity getHoldingActivity() {
        return activity;
    }

    /**
     * 显示进度条
     * notice：基类中实现IBaseView的回调
     */
    public void showProgress() {
        LoadingDialog.show(getHoldingActivity());
    }

    /**
     * 隐藏进度条
     * notice：基类中实现IBaseView的回调
     */
    public void hideProgress() {
        LoadingDialog.close();
    }

}