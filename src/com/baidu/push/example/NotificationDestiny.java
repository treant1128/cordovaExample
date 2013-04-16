package com.baidu.push.example;

import com.baidu.android.pushservice.PushConstants;

import android.app.Activity;
import android.content.Intent;
import android.webkit.WebView;
import android.widget.TextView;

public class NotificationDestiny extends Activity {
	private TextView title=null;
	private WebView webView=null;
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.destiny);
		title=(TextView)findViewById(R.id.title);
		webView=(WebView)findViewById(R.id.webView);
		Intent intent=getIntent();
		String t=intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_TITLE);
		String content=intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT);
		title.setText(t);
		webView.loadUrl(parseURL(content));
	};
	
	private String parseURL(String content){
		if(content.matches("^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$")){
			return content;
		}else{
			return "http://news.wukong.com";
		}
	}
}
