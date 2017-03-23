package com.yun.android_learninggrowth.config;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.yun.android_learninggrowth.app.App;
import com.yun.library.utils.PreferenceUtils;

/**
 * Created by YYF on 2017/3/23.
 */

//Preferencec常用类 用于保存一下简单数据
public class PreferenceConfig {
    private static App sInstance = App.getInstance();

    //清除所有preference数据
    public static void clearAllPreference(){
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(sInstance);
        PreferenceUtils.clearPreference(sInstance, settings);
    }

    /**
     * 第一次启动App
     */
    public static final String FIRST_START_APP = "first_start_app";

    public static boolean getFirstStartApp() {
        return PreferenceUtils.getPrefBoolean(sInstance, FIRST_START_APP, true);
    }

    public static void setFirstStartApp(boolean isFirstStart) {
        PreferenceUtils.setPrefBoolean(sInstance, FIRST_START_APP, isFirstStart);
    }
}
