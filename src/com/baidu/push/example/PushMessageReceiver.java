package com.baidu.push.example;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.android.pushservice.PushConstants;

/**
 * Push��Ϣ����receiver
 */
public class PushMessageReceiver extends BroadcastReceiver {
	/** TAG to Log */
	public static final String TAG = PushMessageReceiver.class.getSimpleName();

	AlertDialog.Builder builder;
	
	@Override
	public void onReceive(final Context context, Intent intent) {

//		Log.d("11", "-"+intent.getExtras().getString(PushConstants.EXTRA_ACCESS_TOKEN));
//		Log.d("22", "-"+intent.getExtras().getString(PushConstants.EXTRA_API_KEY));
//		Log.d("33", "-"+intent.getExtras().getString(PushConstants.EXTRA_APP));
//		Log.d("44", "-"+intent.getExtras().getString(PushConstants.EXTRA_APP_ID));
//		Log.d("55", "-"+intent.getExtras().getString(PushConstants.EXTRA_BIND_NAME));
//		Log.d("66", "-"+intent.getExtras().getString(PushConstants.EXTRA_BIND_STATUS));
//		Log.d("77", "-"+intent.getExtras().getString(PushConstants.EXTRA_CB_URL));
//		Log.d("88", "-"+intent.getExtras().getString(PushConstants.EXTRA_CONTENT));
//		Log.d("99", "-"+intent.getExtras().getString(PushConstants.EXTRA_ERROR_CODE));
//		Log.d("10", "-"+intent.getExtras().getString(PushConstants.EXTRA_FETCH_NUM));
//		Log.d("111", "-"+intent.getExtras().getString(PushConstants.EXTRA_FETCH_TYPE));
//		Log.d("112", "-"+intent.getExtras().getString(PushConstants.EXTRA_GID));
//		Log.d("113", "-"+intent.getExtras().getString(PushConstants.EXTRA_GROUP_FETCH_NUM));
//		Log.d("114", "-"+intent.getExtras().getString(PushConstants.EXTRA_GROUP_FETCH_TYPE));
//		Log.d("115", "-"+intent.getExtras().getString(PushConstants.EXTRA_HASHCODE));
//		Log.d("116", "-"+intent.getExtras().getString(PushConstants.EXTRA_METHOD));
//		Log.d("117", "-"+intent.getExtras().getString(PushConstants.EXTRA_MSG));
//		Log.d("118", "-"+intent.getExtras().getString(PushConstants.EXTRA_MSG_IDS));
//		Log.d("119", "-"+intent.getExtras().getString(PushConstants.EXTRA_MSG_KEY));
//		Log.d("1111", "-"+intent.getExtras().getString(PushConstants.EXTRA_MSGID));
//		Log.d("1112", "-"+intent.getExtras().getString(PushConstants.EXTRA_NOTIFICATION_CONTENT));
//		Log.d("1113", "-"+intent.getExtras().getString(PushConstants.EXTRA_NOTIFICATION_TITLE));
//		
//		Log.d("1114", "-"+intent.getExtras().getString(PushConstants.EXTRA_OPENTYPE));
//		Log.d("1115", "-"+intent.getExtras().getString(PushConstants.EXTRA_PUSH_MESSAGE));
//		Log.d("1116", "-"+intent.getExtras().getString(PushConstants.EXTRA_PUSH_MESSAGE_STRING));
//		Log.d("1117", "-"+intent.getExtras().getString(PushConstants.EXTRA_TAGS));
//		Log.d("1118", "-"+intent.getExtras().getString(PushConstants.EXTRA_TIMESTAMP));
	//	Log.d("1119", "-"+intent.getExtras().getString(PushConstants.));
		
		//��������
//		final String content2 = new String(
//				intent.getByteArrayExtra(PushConstants.EXTRA_CONTENT));
		//Log.d("LLLLLLLLLLLLLLLLLLLLLL",intent.);
		//Log.d(TAG, ">>> Receive intent: \r\n" + intent);

//		Log.d("LLLLLLLLLLLLLLLLLLLLLL", intent.getAction());
			if (intent.getAction().equals(PushConstants.ACTION_RECEIVE)) {
			//��������
//			String content1 = intent.getExtras().getString(PushConstants.ACTION_RECEIVER_NOTIFICATION_CLICK);
//			Log.d("aaaaaaaaaaaaaa", "onMessage: content ����: " + content1);
//			String content2 = intent.getExtras().getString(PushConstants.EXTRA_NOTIFICATION_CONTENT);
//			Log.d("BBBBBBBBBBBBBBB", "onMessage: content ����: " + content2);
//			String content3 = intent.getExtras().getString(PushConstants.EXTRA_PUSH_MESSAGE);
//			Log.d("CCCCCCCCCCCCCCCC", "onMessage: content ����: " + content3);
			
		//��ѡ��֪ͨ�û�����¼�����
		}
		if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
			
			//�û��ڴ��Զ��崦����Ϣ,���´���Ϊdemo����չʾ��
			String message = intent.getExtras().getString(
					PushConstants.EXTRA_PUSH_MESSAGE_STRING);
			
			String title=message.substring(0, message.indexOf(" "));
			String content=message.substring(message.lastIndexOf(" "));
			
			Log.d("�㲥���յ���", "title="+title+", content="+content);
			
			Intent i = new Intent();  
		    //���µ�����������Activity  
		    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		    //����Intent�������������  
		    i.setClass(context, TriggerService.class);  
		    i.putExtra(PushConstants.EXTRA_NOTIFICATION_TITLE, title);
		    i.putExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT, content);
		    context.startService(i); 
		} 
		else if (intent.getAction().equals(
				PushConstants.ACTION_RECEIVER_NOTIFICATION_CLICK)) {

			Log.d(TAG, "intent��369��uri=" + intent.toUri(0));
			Intent aIntent = new Intent();
			aIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			aIntent.setClass(context, NotificationDestiny.class);
			String title = intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_TITLE);
			aIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_TITLE, title);
			String content = intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT);
			aIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT, content);

			context.startActivity(aIntent);
		}
	}

}
