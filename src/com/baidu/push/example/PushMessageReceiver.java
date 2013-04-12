package com.baidu.push.example;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;

/**
 * Push消息处理receiver
 */
public class PushMessageReceiver extends BroadcastReceiver {
	/** TAG to Log */
	public static final String TAG = PushMessageReceiver.class.getSimpleName();

	AlertDialog.Builder builder;

	/**
	 * 
	 * 
	 * @param context
	 *            Context
	 * @param intent
	 *            接收的intent
	 */
	@Override
	public void onReceive(final Context context, Intent intent) {

		Log.d(TAG, ">>> Receive intent: \r\n" + intent);

		if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
			//获取消息内容
			String message = intent.getExtras().getString(
					PushConstants.EXTRA_PUSH_MESSAGE_STRING);

			//用户自定义内容读取方式，CUSTOM_KEY值和管理界面发送时填写的key对应
			//String customContentString = intent.getExtras().getString(CUSTOM_KEY);
			Log.i(TAG, "onMessage: " + message);

			//用户在此自定义处理消息,以下代码为demo界面展示用
//			Intent responseIntent = null;
//			responseIntent = new Intent(PushDemoActivity.ACTION_MESSAGE);
//			responseIntent.putExtra(PushDemoActivity.EXTRA_MESSAGE, message);
//			responseIntent.setClass(context, PushDemoActivity.class);
//			responseIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startActivity(responseIntent);

		//处理绑定等方法的返回数据
		//注:PushManager.startWork()的返回值通过PushConstants.METHOD_BIND得到
		} else if (intent.getAction().equals(PushConstants.ACTION_RECEIVE)) {
			//获取方法
			final String method = intent
					.getStringExtra(PushConstants.EXTRA_METHOD);
			//方法返回错误码,您需要恰当处理。比如，方法为bind时，若失败，需要重新bind,即重新调用startWork
			final int errorCode = intent
					.getIntExtra(PushConstants.EXTRA_ERROR_CODE,
							PushConstants.ERROR_SUCCESS);
			//返回内容
			final String content = new String(
					intent.getByteArrayExtra(PushConstants.EXTRA_CONTENT));
			
			//用户在此自定义处理消息,以下代码为demo界面展示用
			
			Log.d(TAG, "onMessage: method 方法: " + method);
			Log.d(TAG, "onMessage: result 结果: " + errorCode);
			Log.d(TAG, "onMessage: content 内容: " + content);
			Toast.makeText(
					context,
					"method : " + method + "\n result: " + errorCode
							+ "\n content = " + content, Toast.LENGTH_SHORT)
					.show();

//			Intent responseIntent = null;
//			responseIntent = new Intent(PushDemoActivity.ACTION_RESPONSE);
//			responseIntent.putExtra(PushDemoActivity.RESPONSE_METHOD, method);
//			responseIntent.putExtra(PushDemoActivity.RESPONSE_ERRCODE,
//					errorCode);
//			responseIntent.putExtra(PushDemoActivity.RESPONSE_CONTENT, content);
//			responseIntent.setClass(context, PushDemoActivity.class);
//			responseIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startActivity(responseIntent);
			
		//可选。通知用户点击事件处理
		} else if (intent.getAction().equals(
				PushConstants.ACTION_RECEIVER_NOTIFICATION_CLICK)) {
			Log.d(TAG, "intent内容uri=" + intent.toUri(0));
			Intent aIntent = new Intent();
			aIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			aIntent.setClass(context, cordovaExample.class);
			String title = intent
					.getStringExtra(PushConstants.EXTRA_NOTIFICATION_TITLE);
			aIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_TITLE, title);
			String content = intent
					.getStringExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT);
			aIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT, content);

			context.startActivity(aIntent);
		}
	}

}
