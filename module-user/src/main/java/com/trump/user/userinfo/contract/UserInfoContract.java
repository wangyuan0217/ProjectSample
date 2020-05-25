package com.trump.user.userinfo.contract;

import com.trump.library_common.model.response.UserInfo;
import com.trump.library_common.ui.base.view.IBasePresenter;
import com.trump.library_common.ui.base.view.IBaseView;

/**
 * @author 王元_Trump
 * @time 2020/05/25 17:07
 * @desc
 */
public class UserInfoContract {
    public interface View extends IBaseView {
        void showUserInfo(UserInfo userInfo);
    }

    public interface Presenter extends IBasePresenter<View> {
        void getUserInfo(String userId);
    }

}
