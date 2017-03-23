package com.yun.android_learninggrowth.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

import com.yun.android_learninggrowth.app.App;


public class TUtils {

    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast = null;
    private static Object synObj = new Object();

    /**
     * Toast发送消息，默认Toast.LENGTH_SHORT
     *
     * @param act
     * @param msg
     * @author WikerYong   Email:<a href="#">yw_312@foxmail.com</a>
     * @version 2012-5-22 上午11:13:10
     */
    public static void showToast(final Context act, final String msg) {
        showMessage(act, msg, Toast.LENGTH_SHORT);
    }

    public static void showToast(final String msg) {
        showMessage(App.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT);
    }

    public static void showToast(@StringRes int res) {
        showToast(App.getInstance().getApplicationContext().getResources().getString(res));
    }

    /**
     * Toast发送消息，默认Toast.LENGTH_LONG
     *
     * @param act
     * @param msg
     */
    public static void showToastLong(final Context act, final String msg) {
        showMessage(act, msg, Toast.LENGTH_LONG);
    }

    public static void showToastLong(final String msg) {
        showMessage(App.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT);
    }

    /**
     * Toast发送消息，默认Toast.LENGTH_SHORT
     *
     * @param act
     * @param msg
     */
    public static void showToast(final Context act, final int msg) {
        showMessage(act, msg, Toast.LENGTH_SHORT);
    }

    /**
     * Toast发送消息，默认Toast.LENGTH_LONG
     *
     * @param act
     * @param msg
     */
    public static void showToastLong(final Context act, final int msg) {
        showMessage(act, msg, Toast.LENGTH_LONG);
    }

    /**
     * Toast发送消息
     *
     * @param act
     * @param msg
     * @param len
     */
    public static void showMessage(final Context act, final int msg,
                                   final int len) {
        new Thread(new Runnable() {
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        synchronized (synObj) {
                            if (toast != null) {
                                toast.setText(msg);
                                toast.setDuration(len);
                            } else {
                                toast = Toast.makeText(act, msg, len);
                            }
                            toast.show();
                        }
                    }
                });
            }
        }).start();
    }

    /**
     * Toast发送消息
     *
     * @param act
     * @param msg
     * @param len
     */
    public static void showMessage(final Context act, final String msg,
                                   final int len) {
        new Thread(new Runnable() {
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        synchronized (synObj) {
                            if (toast != null) {
                                toast.setText(msg);
                                toast.setDuration(len);
                            } else {
                                toast = Toast.makeText(act, msg, len);

                            }
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                });
            }
        }).start();
    }

    /**
     * 关闭当前Toast
     */
    public static void cancelCurrentToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
