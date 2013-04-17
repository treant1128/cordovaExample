package com.baidu.push.example;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.baidu.android.common.logging.Log;
import com.baidu.android.pushservice.PushConstants;

public class TriggerService extends IntentService {

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
		Log.e("#########", "zzzzzz");
		String title = intent.getStringExtra(
				PushConstants.EXTRA_NOTIFICATION_TITLE);
		String content = intent.getStringExtra(
				PushConstants.EXTRA_NOTIFICATION_CONTENT);
		
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				TriggerService.this).setSmallIcon(R.drawable.icon)
				.setContentTitle(title).setContentText(content);
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(TriggerService.this,
				NotificationDestiny.class);
		resultIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_TITLE, title);
		resultIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT, content);
		// The stack builder object will contain an artificial back stack for
		// the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder
				.create(TriggerService.this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(NotificationDestiny.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(888, mBuilder.build());
	}

}
