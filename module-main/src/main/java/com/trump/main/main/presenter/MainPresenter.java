package com.trump.main.main.presenter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.trump.library_common.http.provider.NewsProvider;
import com.trump.library_common.http.rxJava.RxConsumer;
import com.trump.library_common.http.rxJava.RxThrowableConsumer;
import com.trump.library_common.model.response.NewsType;
import com.trump.library_common.rx.RxScheduler;
import com.trump.library_common.ui.base.presenter.BasePresenter;
import com.trump.main.main.contract.MainContract;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author 王元_Trump
 * @time 2020/03/20 16:10
 * @desc
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Autowired
    NewsProvider mNewsProvider;

    public MainPresenter() {
        ARouter.getInstance().inject(this);
    }

    @Override
    public void doHttp() {
        Disposable disposable = mNewsProvider.getNewsType()
                .compose(RxScheduler.inIoMainLoading(getContext()))
                .subscribe(new RxConsumer<List<NewsType>>() {

                    @Override
                    public void _accept(List<NewsType> newsTypes) {
                        getMvpView().doHttpSuccess(newsTypes);
                    }

                }, new RxThrowableConsumer());
        addTask(disposable);
    }
}
