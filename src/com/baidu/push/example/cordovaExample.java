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

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;

import org.apache.cordova.*;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

public class cordovaExample extends DroidGap
{
<<<<<<< HEAD
	public NotificationDestiny mc;
	
    @Override
=======
    @SuppressLint("SetJavaScriptEnabled")
	@Override
>>>>>>> release
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
        
    }
    
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK){
	      	ConfirmExit();//���˷��ؼ������Ѿ����ܷ��أ���ִ���˳�ȷ��
	       	return true; 
        }
		return false;   
    };
    
    public void ConfirmExit(){//�˳�ȷ��
    	AlertDialog.Builder ad=new AlertDialog.Builder(cordovaExample.this);
    	ad.setTitle("�˳�");
    	ad.setMessage("�Ƿ��˳�?");
    	ad.setPositiveButton("��", new DialogInterface.OnClickListener() {//�˳���ť
			@Override
			public void onClick(DialogInterface dialog, int i) {
				// TODO Auto-generated method stub
				cordovaExample.this.finish();//�ر�activity
 
			}
		});
    	ad.setNegativeButton("��",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int i) {
				//���˳�����ִ���κβ���
			}
		});
    	ad.show();//��ʾ�Ի���
    }
}

