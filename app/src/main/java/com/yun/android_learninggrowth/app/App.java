package com.yun.android_learninggrowth.app;

import android.support.multidex.MultiDexApplication;

import com.yun.android_learninggrowth.BuildConfig;

/**
 * Created by SZITTom on 2017/3/23.
 */
//突破65536限制继承MultiDexApplication，或者不继承重写attachBaseContext()方法
public class App extends MultiDexApplication {

    //设置为单例模式
    private static App sInstance;

    public static App getInstance() {
        if (sInstance == null) {
            synchronized (App.class) {
                if (sInstance == null) {
                    sInstance = new App();
                }
            }
        }
        return sInstance;
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        //DEBUG或者REALSES模式下的初始化设置
        if (BuildConfig.DEBUG) {

        } else {

        }
    }
}
