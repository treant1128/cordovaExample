package com.baidu.push.example;

import org.apache.cordova.DroidGap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;

public class NotificationDestiny extends Activity {
	private WebView wv;
	private ProgressDialog pd;
	private Handler handler;
	
	@Override
	public void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.destiny);
		processExtraData();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		String title=intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_TITLE);
		String content=intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT);
		Toast.makeText(this, title+"-"+content, 3).show();
		super.onNewIntent(intent);
		setIntent(intent);
		processExtraData();
	}
	
	private void processExtraData() {
		init();//执行初始化函数
		wv=(WebView)findViewById(R.id.webView);
		Intent intent=getIntent();
		String t=intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_TITLE);
		String url=intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT);
		
		Log.d("目的地的", "title="+t+", content="+url);
		setTitle(t);
		loadurl(wv,url);
        handler=new Handler(){
        	public void handleMessage(Message msg)
    	    {//定义Handler，处理下载线程与UI间通讯
    	      if (!Thread.currentThread().isInterrupted())
    	      {
    	        switch (msg.what)
    	        {
    	        case 0:
    	        	pd.show();        	
    	        	break;
    	        case 1:
    	        	pd.hide();//隐藏进度对话框，不可使用dismiss()、cancel(),否则再次调用show()时，显示的对话框小圆圈不会动。
    	        	break;
    	        }
    	      }
    	      super.handleMessage(msg);
    	    }
        };
//		wv.loadUrl(url);
//		super.loadUrl(url);
	};
	
//	public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回键
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {   
//            wv.goBack();   
//            return true;   
//        }else if(keyCode == KeyEvent.KEYCODE_BACK){
//        	ConfirmExit();//按了返回键，但已经不能返回，则执行退出确认
//        	return true; 
//        }   
//        return super.onKeyDown(keyCode, event);   
//    }
//    public void ConfirmExit(){//退出确认
//    	AlertDialog.Builder ad=new AlertDialog.Builder(NotificationDestiny.this);
//    	ad.setTitle("退出");
//    	ad.setMessage("是否退出?");
//    	ad.setPositiveButton("是", new DialogInterface.OnClickListener() {//退出按钮
//			@Override
//			public void onClick(DialogInterface dialog, int i) {
//				// TODO Auto-generated method stub
//				NotificationDestiny.this.finish();//关闭activity
// 
//			}
//		});
//    	ad.setNegativeButton("否",new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int i) {
//				//不退出不用执行任何操作
//			}
//		});
//    	ad.show();//显示对话框
//    }
	
    @SuppressLint("SetJavaScriptEnabled")
	public void init(){//初始化
    	wv=(WebView)findViewById(R.id.webView);
        wv.getSettings().setJavaScriptEnabled(true);//可用JS
        wv.setScrollBarStyle(0);//滚动条风格，为0不给滚动条留空间，覆盖在网页上
        wv.setWebViewClient(new WebViewClient(){   
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
            	loadurl(view,url);//载入网页
                return true;   
            }//重写点击动作,用webview载入
 
        });
        wv.setWebChromeClient(new WebChromeClient(){
        	public void onProgressChanged(WebView view,int progress){//载入进度改变而触发 
             	if(progress==100){
            		handler.sendEmptyMessage(1);//如全部载入,隐藏progressbar
            	}   
                super.onProgressChanged(view, progress);   
            }   
        });
 
    	pd=new ProgressDialog(NotificationDestiny.this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("数据载入中，请稍候...");
    }
    
    public void loadurl(final WebView view,final String url){
    	new Thread(){
    		@Override
        	public void run(){
        		handler.sendEmptyMessage(0);
        		view.loadUrl(url);
        	}
        }.start();
    }
}
