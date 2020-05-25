package com.trump.sample.main.activity;

import android.content.Intent;
import android.view.KeyEvent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.trump.library_common.model.response.NewsType;
import com.trump.library_common.router.ActivityJumper;
import com.trump.library_common.ui.adapter.MyFragmentAdapter;
import com.trump.library_common.ui.base.activity.BaseActivity;
import com.trump.library_common.utils.AppManager;
import com.trump.sample.R;
import com.trump.sample.main.contract.MainContract;
import com.trump.sample.main.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

@Route(path = "/app/main")
public class MainActivity extends BaseActivity<MainContract.View, MainContract.Presenter>
        implements MainContract.View {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initData() {
        int defaultShowPage = getIntent().getIntExtra("last_page", 0);

        mNavigation.setItemIconTintList(null); //使用源图标颜色

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(ActivityJumper.getFragmentMain());
        fragments.add(ActivityJumper.getFragmentMine());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), mContext, fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(defaultShowPage);

        mViewPager.setOffscreenPageLimit(fragments.size());

        mNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);//首页
                    return true;
                case R.id.navigation_mine:
                    mViewPager.setCurrentItem(1);//我的
                    return true;
            }
            return false;
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mNavigation.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int defaultShowPage = intent.getIntExtra("last_page", 0);
        try {
            mViewPager.setCurrentItem(defaultShowPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long mkeyTime;// 按两次返回键退出的时间记录

    /**
     * 屏蔽返回键
     **/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mkeyTime) > 2000) {
                mkeyTime = System.currentTimeMillis();
                showToast(getString(R.string.tip_exit_on_main));
            } else {
                MainActivity.this.finish();
                AppManager.getAppManager().AppExit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void doHttpSuccess(List<NewsType> newsTypes) {

    }
}
