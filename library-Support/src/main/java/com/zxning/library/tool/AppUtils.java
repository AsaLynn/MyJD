package com.zxning.library.tool;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Method;

/**
 * 用于调用系统方法.
 */
public class AppUtils {

    /**
     * 回结果就是设备信息DeviceId
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        try {
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = null;
            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                device_id = tm.getDeviceId();
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
            }
            return device_id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取设备的信息
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class<?> clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                if (rest == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                } else {
                    result = false;
                }
            } catch (Exception e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }

    public static void startPhotoZoom(Uri uri, Activity activity,int requestCode) {
        Intent intent = getIntentForPhotoZoom(uri);
        activity.startActivityForResult(intent, requestCode);
    }


    /**
     * 调用系统裁剪图片
     *
     * @param uri
     */
    public static void startPhotoZoom(Uri uri, android.support.v4.app.Fragment fragment,int requestCode) {
        Intent intent = getIntentForPhotoZoom(uri);
        fragment.startActivityForResult(intent, requestCode);
    }

    /**
     * 打开系统裁剪的意图.
     *
     * @param uri
     * @return
     */
    public static Intent getIntentForPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", UIUtils.px2dip(100));
        intent.putExtra("outputY", UIUtils.px2dip(100));
        intent.putExtra("return-data", true);
        return intent;
    }


    // 打开手机照相机
    public static void openCamera(android.support.v4.app.Fragment fragment, int requestCode, File file) {
        // 打开相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用,存储缓存图片
        if (AppUtils.hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        fragment.startActivityForResult(intent, requestCode);
    }

    // 打开手机照相机,
    public static void openCamera(Activity activity, int requestCode, File file) {
        // 打开相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用,存储缓存图片
        if (AppUtils.hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * @return true表示 有sdcard, false表示没有sdcard
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    //打开手机系统的相册
    public static void openMobilePhotoAlbumOn(android.support.v4.app.Fragment fragment, int requestCode) {// openMobilePhotoAlbum
        Intent intentFromGallery = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentFromGallery.setType("image/*"); // 设置文件类型
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        fragment.startActivityForResult(intentFromGallery, requestCode);
    }

    public static void openMobilePhotoAlbum(Activity activity, int requestCode) {// openMobilePhotoAlbum,
        Intent intentFromGallery = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentFromGallery.setType("image/*"); // 设置文件类型
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intentFromGallery, requestCode);
    }

}
