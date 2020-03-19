package com.trump.library_common.ui.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.trump.library_common.R;

/**
 * @author 王元_Trump
 * @time 2020/03/19 16:17
 * @desc
 */
public class LoadingDialog {
    /**
     * 对话框
     */
    private static Dialog loadingDialog;
    private static ImageView ivLoading;
    private static Animation animation;


    public static void show(Context context) {
        if (loadingDialog != null) {
            release();
        }

//        if (loadingDialog == null) {
//            createLoadingDialog(context);
//        }
        createLoadingDialog(context);
        loadingDialog.show();
    }

    private static void createLoadingDialog(Context context) {
        try {
            loadingDialog = new Dialog(context, R.style.TransparentDialog);
            View loadView = LayoutInflater.from(context).inflate(R.layout.common_dialog_loading, null);
            ivLoading = loadView.findViewById(R.id.iv_loading);
            animation = AnimationUtils.loadAnimation(context, R.anim.progress_drawable_white);
            ivLoading.startAnimation(animation);//开始动画
            loadingDialog.setContentView(loadView);
            loadingDialog.setCanceledOnTouchOutside(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            if (loadingDialog != null) {
                ivLoading.clearAnimation();
                loadingDialog.dismiss();
                loadingDialog = null;
                ivLoading = null;
                animation = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void release() {
        try {
            if (loadingDialog != null) {
                ivLoading.clearAnimation();
                loadingDialog.dismiss();
                loadingDialog = null;
                ivLoading = null;
                animation = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}