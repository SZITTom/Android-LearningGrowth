package com.yun.android_learninggrowth.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.view.activity.MessageActivity;

/**
 * Created by SZITTom on 2017/3/24.
 * 轮询服务 http://blog.csdn.net/ryantang03/article/details/9317499
 * 添加一些手机上黑屏后保持后台servic唤醒 使用WakeLock  http://blog.csdn.net/ryantang03/article/details/8628753
 */

public class PollingService extends Service {

    public static final String ACTION = "com.yun.android_learninggrowth.service.PollingService";

    private Notification mNotification;
    private NotificationManager mManager;
    private NotificationCompat.Builder cBuilder;
    private PowerManager.WakeLock wakeLock;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initNotifiManager();
        acquireWakeLock();
    }

    //@IntDef(value = {Service.START_FLAG_REDELIVERY, Service.START_FLAG_RETRY}, flag = true)
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent,flags,startId);
//    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        new PollingThread().start();
    }

    //初始化通知栏配置
    private void initNotifiManager() {
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        cBuilder = new NotificationCompat.Builder(this);
        int icon = R.mipmap.ic_launcher;
        cBuilder.setWhen(System.currentTimeMillis());
        cBuilder.setSmallIcon(icon);
        cBuilder.setTicker("New Message");
        cBuilder.setDefaults(Notification.DEFAULT_SOUND);
        cBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        cBuilder.setAutoCancel(true);

    }

    //弹出Notification
    private void showNotification() {
        //Navigator to the new activity when click the notification title
        Intent i = new Intent(this, MessageActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i,
                Intent.FLAG_ACTIVITY_NEW_TASK);
        cBuilder.setContentTitle(getResources().getString(R.string.app_name));
        cBuilder.setContentText("You have new message!");
        cBuilder.setContentIntent(pendingIntent);
        mNotification = cBuilder.build();
        mManager.notify(0, mNotification);
    }

    /**
     * Polling thread
     * 模拟向Server轮询的异步线程
     *
     * @Author Ryan
     * @Create 2013-7-13 上午10:18:34
     */
    int count = 0;

    class PollingThread extends Thread {

        @Override
        public void run() {
            count++;
            System.out.println("Polling....." + count);
            //当计数能被5整除时弹出通知
            if (count % 5 == 0) {
                showNotification();
                System.out.println("New message!");
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mManager != null) {
            mManager.cancelAll();
            mManager = null;
        }
        releaseWakeLock();
        System.out.println("Service:onDestory");
    }

    //获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
    private void acquireWakeLock() {
        if (null == wakeLock) {
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "PostLocationService");
            if (null != wakeLock) {
                wakeLock.acquire();
                System.out.println("wakeLock:start");
            }
        }
    }

    //释放设备电源锁
    private void releaseWakeLock() {
        if (null != wakeLock) {
            wakeLock.release();
            System.out.println("wakeLock:release");
            wakeLock = null;
        }
    }
}
