package com.baidu.push.example;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;

/**
 * Push��Ϣ����receiver
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
	 *            ���յ�intent
	 */
	@Override
	public void onReceive(final Context context, Intent intent) {

		Log.d(TAG, ">>> Receive intent: \r\n" + intent);

		if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
			//��ȡ��Ϣ����
			String message = intent.getExtras().getString(
					PushConstants.EXTRA_PUSH_MESSAGE_STRING);

			//�û��Զ������ݶ�ȡ��ʽ��CUSTOM_KEYֵ�͹�����淢��ʱ��д��key��Ӧ
			//String customContentString = intent.getExtras().getString(CUSTOM_KEY);
			Log.i(TAG, "onMessage: " + message);

			//�û��ڴ��Զ��崦����Ϣ,���´���Ϊdemo����չʾ��
//			Intent responseIntent = null;
//			responseIntent = new Intent(PushDemoActivity.ACTION_MESSAGE);
//			responseIntent.putExtra(PushDemoActivity.EXTRA_MESSAGE, message);
//			responseIntent.setClass(context, PushDemoActivity.class);
//			responseIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startActivity(responseIntent);

		//����󶨵ȷ����ķ�������
		//ע:PushManager.startWork()�ķ���ֵͨ��PushConstants.METHOD_BIND�õ�
		} else if (intent.getAction().equals(PushConstants.ACTION_RECEIVE)) {
			//��ȡ����
			final String method = intent
					.getStringExtra(PushConstants.EXTRA_METHOD);
			//�������ش�����,����Ҫǡ���������磬����Ϊbindʱ����ʧ�ܣ���Ҫ����bind,�����µ���startWork
			final int errorCode = intent
					.getIntExtra(PushConstants.EXTRA_ERROR_CODE,
							PushConstants.ERROR_SUCCESS);
			//��������
			final String content = new String(
					intent.getByteArrayExtra(PushConstants.EXTRA_CONTENT));
			
			//�û��ڴ��Զ��崦����Ϣ,���´���Ϊdemo����չʾ��
			
			Log.d(TAG, "onMessage: method ����: " + method);
			Log.d(TAG, "onMessage: result ���: " + errorCode);
			Log.d(TAG, "onMessage: content ����: " + content);
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
			
		//��ѡ��֪ͨ�û�����¼�����
		} else if (intent.getAction().equals(
				PushConstants.ACTION_RECEIVER_NOTIFICATION_CLICK)) {
			Log.d(TAG, "intent����uri=" + intent.toUri(0));
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
