package com.baidu.push.example;

import org.apache.cordova.DroidGap;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;

public class NotificationDestiny extends DroidGap {
//	private TextView title=null;
//	private WebView webView=null;
	@Override
	public void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.destiny);
//		title=(TextView)findViewById(R.id.title);
//		webView=(WebView)findViewById(R.id.webView);
		Intent intent=getIntent();
		String t=intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_TITLE);
		String url=intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT);
		
		Log.i("Ìâ±ê",t);Log.i("ÈÝÄÚ",url);
		
//		title.setText(t);
//		webView.loadUrl(url);
		super.loadUrl(url);
	};
	
	private String parseURL(String content){
		if(content.matches("^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$")){
			return content;
		}else{
			return "http://news.wukong.com";
		}
	}
}
