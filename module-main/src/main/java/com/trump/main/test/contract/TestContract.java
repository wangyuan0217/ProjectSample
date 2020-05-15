package com.trump.main.test.contract;

import com.trump.library_common.ui.base.view.IBasePresenter;
import com.trump.library_common.ui.base.view.IBaseView;

/**
 * @author 王元_Trump
 * @time 2020/03/20 15:52
 * @desc
 */
public class TestContract {

    public interface View extends IBaseView {

    }

    public interface Presenter extends IBasePresenter<View> {

    }

}
