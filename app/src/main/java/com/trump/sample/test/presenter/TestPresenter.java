package com.trump.sample.test.presenter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.trump.library_common.http.provider.NewsProvider;
import com.trump.library_common.ui.base.presenter.BasePresenter;
import com.trump.sample.test.contract.TestContract;

/**
 * @author 王元_Trump
 * @time 2020/03/20 16:10
 * @desc
 */
public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {

    @Autowired
    NewsProvider mNewsProvider;

    public TestPresenter() {
        ARouter.getInstance().inject(this);
    }

}
