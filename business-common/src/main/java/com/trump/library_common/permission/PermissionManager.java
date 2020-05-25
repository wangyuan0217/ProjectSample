package com.trump.library_common.permission;

import android.Manifest;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;

/**
 * @author 王元_Trump
 * @time 2020/05/23 16:37
 * @desc
 */
public class PermissionManager {

    /**
     * 请求照相机相册权限
     *
     * @param rxPermissions
     * @param consumer
     */
    public static void requestCameraAlbumPermissions(RxPermissions rxPermissions, PermissionListener consumer) {
        Disposable disposable = rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(consumer);
    }

    /**
     * 请求SD卡读写权限
     *
     * @param rxPermissions
     * @param consumer
     */
    public static void requestStoragePermissions(RxPermissions rxPermissions, PermissionListener consumer) {
        Disposable disposable = rxPermissions.request(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(consumer);
    }

}
