package com.trump.main.main.activity;

import android.view.KeyEvent;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.model.response.NewsType;
import com.trump.library_common.router.ActivityJumper;
import com.trump.library_common.ui.adapter.MyFragmentAdapter;
import com.trump.library_common.ui.base.activity.BaseActivityMVP;
import com.trump.library_common.utils.AppManager;
import com.trump.main.R;
import com.trump.main.databinding.ActivityMainBinding;
import com.trump.main.main.contract.MainContract;
import com.trump.main.main.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/main/main")
public class MainActivity extends BaseActivityMVP<ActivityMainBinding, MainContract.View, MainContract.Presenter>
        implements MainContract.View {

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initData() {
        getBinding().navigation.setItemIconTintList(null); //使用源图标颜色

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(ActivityJumper.getFragmentHome());
        fragments.add(ActivityJumper.getFragmentMine());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), mContext, fragments);
        getBinding().viewPager.setAdapter(adapter);

        getBinding().viewPager.setOffscreenPageLimit(fragments.size());

        getBinding().navigation.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                getBinding().viewPager.setCurrentItem(0);//首页
                return true;
            } else if (itemId == R.id.navigation_mine) {
                getBinding().viewPager.setCurrentItem(1);//我的
                return true;
            }
            return false;
        });

        getBinding().viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getBinding().navigation.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
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
