package com.yun.android_learninggrowth.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;

import com.yun.library.manager.ActivityManager;
import com.yun.library.permission.EasyPermissions;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by SZITTom on 2017/3/23.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    @LayoutRes
    protected abstract int getContentViewId();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);//长按出现复制粘贴栏在顶部占位
        ActivityManager.getInstance().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initData();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().finishActivity(this);
        super.onDestroy();
    }

    /**
     * 获取根view
     *
     * @param context
     * @return
     */
    public static ViewGroup getRootView(Activity context) {
        return (ViewGroup) ((ViewGroup) context.findViewById(android.R.id.content))
                .getChildAt(0);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //android 6.0 权限管理控制
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(List<String> perms) {
        //获取权限成功后处理 success
    }

    @Override
    public void onPermissionsDenied(List<String> perms) {
        //获取权限失败后处理 failure
    }
}
