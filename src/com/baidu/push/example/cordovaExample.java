/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.baidu.push.example;

import android.os.Bundle;
import org.apache.cordova.*;

import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

public class cordovaExample extends DroidGap
{
	public NotificationDestiny mc;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Set by <content src="index.html" /> in config.xml
        
        super.init();

        mc = new NotificationDestiny();
        super.appView.addJavascriptInterface(mc, "MyCls");
        
        super.loadUrl(Config.getStartUrl());
        //super.loadUrl("file:///android_asset/www/index.html")
        
        PushManager.startWork(getApplicationContext(),
				PushConstants.LOGIN_TYPE_API_KEY, "abyyZObK5IHGs1gHPR1GqYhF");
//        PushManager.startWork(getApplicationContext(),
//				PushConstants.LOGIN_TYPE_API_KEY, Utils.getMetaValue(cordovaExample.this, "api_key"));
        
//        initNotification();
        
        super.appView.getSettings().setJavaScriptEnabled(true);
        super.appView.addJavascriptInterface(this, "MyCls");
        
        
        
//        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(layoutId,   
//        layoutIconId, layoutTitleId, layoutTextId); 
//        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);  
//        cBuilder.setNotificationDefaults(Notification.DEFAULT_SOUND  | 
//        Notification.DEFAULT_VIBRATE); 
//        cBuilder.setStatusbarIcon(statusbarIconId); 
//        cBuilder.setLayoutDrawable(notificationIconId); 
//        PushManager.setNotificationBuilder(this, notificationId, cBuilder); 
    }
    
//    private void initNotification(){
//    	 
//    	 
//    	CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(R.layout.bpush_download_progress,  
//    			R.drawable.ic_launcher, R.string.app_name, R.string.app_name); 
//    	cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);  
//    	cBuilder.setNotificationDefaults(Notification.DEFAULT_SOUND  | Notification.DEFAULT_VIBRATE); 
//    	cBuilder.setStatusbarIcon(R.drawable.ic_launcher); 
//    	cBuilder.setLayoutDrawable(R.drawable.ic_launcher); 
//    	PushManager.setNotificationBuilder(this, notificationId, cBuilder); 
//    }
}

