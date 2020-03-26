package com.trump.library_common.ui.base.view;

/**
 * @author 王元_Trump
 * @time 2020/03/19 15:37
 * @desc
 */
public interface IBaseView {

    /**
     * 显示进度条
     */
    void showProgress();

    /**
     * 隐藏进度条
     */
    void hideProgress();

    /**
     * Toast提示
     *
     * @param message
     */
    void showToast(String message);

}
