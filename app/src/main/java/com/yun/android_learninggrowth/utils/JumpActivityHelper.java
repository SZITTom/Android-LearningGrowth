package com.yun.android_learninggrowth.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import java.io.File;
import java.util.List;

public class JumpActivityHelper {

    /* 用来标识请求gallery的activity */
    public static final int PHOTO_PICKED_WITH_DATA = 3021;

    public static void jumpActivity(Context context, Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(context, clazz);
        context.startActivity(intent);
    }

    public static void jumpActivityForResult(Context context, Class<?> clazz, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, clazz);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, requestCode);
        }
    }

    public static void jumpActivity(Context context, Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 拨打电话
     *
     * @param phone
     */
    public static void JumpPhone(Context context, String phone) {
        Intent phoneIntent = new Intent("android.intent.action.CALL",
				Uri.parse("tel:" + phone));
        context.startActivity(phoneIntent);
    }

    /**
     * 跳转到拨号页面
     *
     * @param phone
     */
    public static void JumpPhoneDial(Context context, String phone) {
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
				Uri.parse("tel:" + phone));
        phoneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(phoneIntent);
    }

    /**
     * 更新文件夹
     * @param context
     * @param f
     */
    public static void sendUpdateFile(Context context, File f) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(f);
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

    public static void JumpPickPhotoFromGallery(Activity context) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        context.startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
    }

    public static void JumpWeb(Activity context, String url) {
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(it);
    }

    /**
     * 启动其他的App
     * @param context
     * @param packageName
     * @param className
     */
    public static void JumpOtherApp(Context context, String packageName, String className){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        context.startActivity(intent);
    }

    /**
     * 启动其他的App
     * @param context
     * @param packagename
     */
    public static void doStartApplicationWithPackageName(Context context, String packagename) {

        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return;
        }

        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList = context.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);

        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            // packagename = 参数packname
            String packageName = resolveinfo.activityInfo.packageName;
            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
            String className = resolveinfo.activityInfo.name;
            // LAUNCHER Intent
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            // 设置ComponentName参数1:packagename参数2:MainActivity路径
            ComponentName cn = new ComponentName(packageName, className);

            intent.setComponent(cn);
            context.startActivity(intent);
        }
    }


}
