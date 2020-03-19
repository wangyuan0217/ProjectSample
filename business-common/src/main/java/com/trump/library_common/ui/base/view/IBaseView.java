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
     * 处理消息
     *
     * @param message
     * @param sub_code
     * @param sub_message
     */
    void handleMsg(String message, String sub_code, String sub_message);

}
