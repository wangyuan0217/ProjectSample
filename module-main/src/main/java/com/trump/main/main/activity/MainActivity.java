package com.trump.main.main.activity;

import android.Manifest;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.SlidingTabLayout;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trump.library_common.ui.base.activity.BaseActivity;
import com.trump.library_common.utils.ToastUtil;
import com.trump.main.MainItemFragment;
import com.trump.main.R;
import com.trump.main.R2;
import com.trump.main.main.contract.MainContract;
import com.trump.main.main.presenter.MainPresenter;
import com.trump.model.response.NewsType;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import io.reactivex.disposables.Disposable;

@Route(path = "/main/home")
public class MainActivity extends BaseActivity<MainContract.View, MainContract.Presenter> implements MainContract.View {

    @BindView(R2.id.tabLayout)
    SlidingTabLayout mTabLayout;
    @BindView(R2.id.viewPager)
    ViewPager mViewPager;

    private ArrayList<Fragment> fragmentList;
    private String[] tabTitles;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_main;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getNewsType();
    }

    @Override
    public void newTypeCallback(List<NewsType> newsTypes) {

        fragmentList = new ArrayList<>();
        tabTitles = new String[newsTypes.size()];

        for (int i = 0; i < newsTypes.size(); i++) {
            NewsType newsType = newsTypes.get(i);
            fragmentList.add(new MainItemFragment());
            tabTitles[i] = newsType.getTypeName();
        }

        mTabLayout.setViewPager(mViewPager, tabTitles, mActivity, fragmentList);

    }

    @Override
    protected void initListener() {
        Disposable disposable = new RxPermissions(this)
                .request(Manifest.permission.CAMERA)
                .subscribe(aBoolean -> ToastUtil.show("boolean = " + aBoolean));
    }

}
