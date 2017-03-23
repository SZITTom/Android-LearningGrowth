package com.yun.library.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * Activity 管理类
 * 
 */
public class ActivityManager {

	private static Stack<Activity> activityStack;
	private static ActivityManager instance = null;

	private ActivityManager() {}

	public static ActivityManager getInstance() {
		if (instance == null) {
			synchronized (ActivityManager.class) {
				if (instance == null) {
					instance = new ActivityManager();
				}
			}
		}
		return instance;
	}

	/**
	 * 获取当前Activity栈中元素个数
	 */
	public int getCount() {
		return activityStack.size();
	}

	/**
	 * 添加Activity到栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity（栈顶Activity）
	 */
	public Activity topActivity() {
		if (activityStack == null) {
			throw new NullPointerException(
					"Activity stack is Null,your Activity must extend Activity");
		}
		if (activityStack.isEmpty()) {
			return null;
		}
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 获取当前Activity（栈顶Activity） 没有找到则返回null
	 */
	public Activity findActivity(Class<?> cls) {
		Activity activity = null;
		for (Activity aty : activityStack) {
			if (aty.getClass().equals(cls)) {
				activity = aty;
				break;
			}
		}
		return activity;
	}

	/**
	 * 结束当前Activity（栈顶Activity）
	 */
	public void finishActivity() {
		finishActivity(topActivity());
	}

	/**
	 * 结束指定的Activity(重载)
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();//此处不用finish
			activity = null;
		}
	}

	/**
	 * 结束指定的Activity(重载)
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
	 *
	 * @param cls
	 */
	public void finishOthersActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (!(activity.getClass().equals(cls))) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 应用程序退出
	 */
	public void AppExit() {
		try {
			finishAllActivity();
			// 杀死该应用进程
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
			Runtime.getRuntime().exit(0);
		} catch (Exception e) {
			Runtime.getRuntime().exit(-1);
		}
	}
}
