package com.baidu.push.example;

import org.apache.cordova.DroidGap;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class MyClass {
	  public WebView mAppView;
	  public DroidGap mGap;

	  public MyClass(DroidGap gap, WebView view)
	  {
	    mAppView = view;
	    mGap = gap;
	  }
	  
	  @JavascriptInterface 
	  public String getUrl(){
		 String url = "http://www.baidu.com/";
	     return url;
	  }
}
