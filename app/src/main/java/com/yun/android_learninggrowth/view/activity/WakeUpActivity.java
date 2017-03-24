package com.yun.android_learninggrowth.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.base.BaseAppCompatActivity;
import com.yun.android_learninggrowth.service.PollingService;
import com.yun.android_learninggrowth.utils.PollingUtils;

/**
 * Android保持屏幕常亮唤醒状态
 * 需要开通权限<uses-permission android:name="android.permission.WAKE_LOCK" />
 */
public class WakeUpActivity extends BaseAppCompatActivity {

    private PowerManager.WakeLock wakeLock;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_wake_up;
    }

    @Override
    protected void initData() {
        //acquireWakeLock();
        //Start polling service
        System.out.println("Start polling service...");
        PollingUtils.startPollingService(this, 5, PollingService.class, PollingService.ACTION);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //wakeLock.acquire();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //wakeLock.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Stop polling service...");
        PollingUtils.stopPollingService(this, PollingService.class, PollingService.ACTION);
    }

    //获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
    private void acquireWakeLock() {
        if (null == wakeLock) {
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "PostLocationService");
            if (null != wakeLock) {
                wakeLock.acquire();
            }
        }
    }

    //释放设备电源锁
    private void releaseWakeLock() {
        if (null != wakeLock) {
            wakeLock.release();
            wakeLock = null;
        }
    }

}
