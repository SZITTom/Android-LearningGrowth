package com.yun.android_learninggrowth.app;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.yun.android_learninggrowth.BuildConfig;

import java.net.Proxy;

import cn.dreamtobe.threaddebugger.IThreadDebugger;
import cn.dreamtobe.threaddebugger.ThreadDebugger;
import cn.dreamtobe.threaddebugger.ThreadDebuggers;

/**
 * Created by SZITTom on 2017/3/23.
 */
//突破65536限制继承MultiDexApplication，或者不继承重写attachBaseContext()方法
public class App extends MultiDexApplication {

    private final static String TAG = "App";

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
        /**
        * 仅仅是缓存Application的Context，不耗时
         * * 下载配置
        */
        FileDownloadLog.NEED_LOG = BuildConfig.DEBUG;
        FileDownloader.init(getApplicationContext(), new DownloadMgrInitialParams.InitCustomMaker()
                .connectionCreator(new FileDownloadUrlConnection
                        .Creator(new FileDownloadUrlConnection.Configuration()
                        .connectTimeout(15_000)//设置连接超时。
                        .readTimeout(15_000)//设置读取超时。
                        .proxy(Proxy.NO_PROXY) // 设置代理
                )));
        //以下代码只是为了FileDownloader监控线程池:
        IThreadDebugger debugger = ThreadDebugger.install(
                ThreadDebuggers.create() /** 与已知ThreadDebugger线程类 **/
                        // add Thread Category 添加线程类
                        .add("OkHttp").add("okio").add("Binder")
                        .add(FileDownloadUtils.getThreadPoolName("Network"), "Network")
                        .add(FileDownloadUtils.getThreadPoolName("Flow"), "FlowSingle")
                        .add(FileDownloadUtils.getThreadPoolName("EventPool"), "Event")
                        .add(FileDownloadUtils.getThreadPoolName("LauncherTask"), "LauncherTask")
                        .add(FileDownloadUtils.getThreadPoolName("BlockCompleted"), "BlockCompleted"),

                2000, /** The frequent of Updating Thread Activity information  活动信息的频繁更新线程**/

                new ThreadDebugger.ThreadChangedCallback() {
                    /**
                     * The threads changed callback
                     * 线程改变回调
                     **/
                    @Override
                    public void onChanged(IThreadDebugger debugger) {
                        // callback this method when the threads in this application has changed.
                        // 回调该方法当线程在这个应用程序中发生了变化。
                        Log.d(TAG, debugger.drawUpEachThreadInfoDiff());
                        Log.d(TAG, debugger.drawUpEachThreadSizeDiff());
                        Log.d(TAG, debugger.drawUpEachThreadSize());
                    }
                });

        //DEBUG或者REALSES模式下的初始化设置
        if (BuildConfig.DEBUG) {

        } else {

        }
    }
}
