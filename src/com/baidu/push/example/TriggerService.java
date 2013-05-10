package com.baidu.push.example;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.baidu.android.common.logging.Log;
import com.baidu.android.pushservice.PushConstants;

public class TriggerService extends IntentService {
	int notification_id = 0;
	private NotificationManager nm;
	
//	public TriggerService(String name) {
//		super("DaDingGou");
//		// TODO Auto-generated constructor stub
//	}
	public TriggerService(){
		super("");
	}
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		String title = intent.getStringExtra(
				PushConstants.EXTRA_NOTIFICATION_TITLE);
		String content = intent.getStringExtra(
				PushConstants.EXTRA_NOTIFICATION_CONTENT);
		Log.d("服务onHandleIntent", "title="+title+", content="+content);
//		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
//				TriggerService.this).setSmallIcon(R.drawable.icon)
//				.setContentTitle(title).setContentText(content);
//		// Creates an explicit intent for an Activity in your app
//		Intent resultIntent = new Intent(TriggerService.this,
//				CopyOfcordovaExample.class);
//		resultIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_TITLE, title);
//		resultIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT, content);
//		// The stack builder object will contain an artificial back stack for the started Activity.
//		// This ensures that navigating backward from the Activity leads out of
//		// your application to the Home screen.
//		TaskStackBuilder stackBuilder = TaskStackBuilder.create(TriggerService.this);
//		// Adds the back stack for the Intent (but not the Intent itself)
//		stackBuilder.addParentStack(CopyOfcordovaExample.class);
//		// Adds the Intent that starts the Activity to the top of the stack
//		stackBuilder.addNextIntent(resultIntent);
//		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
//				PendingIntent.FLAG_UPDATE_CURRENT);
//		mBuilder.setContentIntent(resultPendingIntent);
//		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		// mId allows you to update the notification later on.
//		mNotificationManager.notify(888, mBuilder.build());
		nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		notification_id ++;
		showNotification(R.drawable.icon, title, title, content);
	}
	
	public void showNotification(int icon, String tickertext, String title,
			String content) {
		Notification notification = new Notification(icon, tickertext,
				System.currentTimeMillis());
		
		notification.defaults = Notification.DEFAULT_ALL;
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		
		Intent i=new Intent(this, NotificationDestiny.class);
		i.putExtra(PushConstants.EXTRA_NOTIFICATION_TITLE, title);
		i.putExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT, content);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		Log.d("放进去的", "title="+title+", content="+content);
		PendingIntent pt = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
		
		notification.setLatestEventInfo(this, title, content, pt);
		nm.notify(notification_id, notification);

	}
}
