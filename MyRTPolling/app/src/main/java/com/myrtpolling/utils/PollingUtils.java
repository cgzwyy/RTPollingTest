package com.myrtpolling.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
/**
 * Polling Tools
 * @Author Ryan
 * @Create 2013-7-13 上午10:14:43
 */
public class PollingUtils {

	/**
	 * @param context
	 * @param seconds
	 * @param cls
	 * @param action
	 */
	//开启轮询服务
	public static void startPollingService(Context context, int seconds, Class<?> cls,String action) {
		//获取AlarmManager系统服务
		AlarmManager manager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		//包装需要执行Service的Intent
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		//出发服务的起始时间
		long triggerAtTime = SystemClock.elapsedRealtime(); //获取从系统启动到现在的时间
		//使用alarmManager的setRepeating方法设置定期执行的时间间隔（second秒）和需要执行的Service
		//setRepeating方法用于设置重复闹钟，第一个参数表示闹钟类型，第二个参数表示闹钟首次执行时间，
		// 第三个参数表示闹钟两次执行的间隔时间，第三个参数表示闹钟响应动作。
		manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime,
				seconds * 1000, pendingIntent);
	}

	/**
	 * 
	 * @param context
	 * @param cls
	 * @param action
	 */
	//取消轮询服务
	public static void stopPollingService(Context context, Class<?> cls,String action) {
		AlarmManager manager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		//取消正在执行的服务
		manager.cancel(pendingIntent);
	}
}
