package com.trump.sample.main.contract;

import com.trump.library_common.model.response.NewsType;
import com.trump.library_common.ui.base.view.IBasePresenter;
import com.trump.library_common.ui.base.view.IBaseView;

import java.util.List;

/**
 * @author 王元_Trump
 * @time 2020/03/20 15:52
 * @desc
 */
public class MainContract {

    public interface View extends IBaseView {

        void doHttpSuccess(List<NewsType> newsTypes);
    }

    public interface Presenter extends IBasePresenter<View> {

        void doHttp();
    }

}
