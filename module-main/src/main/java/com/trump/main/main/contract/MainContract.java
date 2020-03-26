package com.trump.main.main.contract;

import com.trump.library_common.ui.base.view.IBasePresenter;
import com.trump.library_common.ui.base.view.IBaseView;
import com.trump.model.response.NewsType;

import java.util.List;

/**
 * @author 王元_Trump
 * @time 2020/03/20 15:52
 * @desc
 */
public class MainContract {

    public interface View extends IBaseView {

        void newTypeCallback(List<NewsType> newsTypes);
    }

    public interface Presenter extends IBasePresenter<View> {

        void getNewsType();
    }

}
