package com.baidu.push.example;

import org.apache.cordova.DroidGap;

import android.annotation.SuppressLint;
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
import com.baidu.android.pushservice.PushConstants;

public class NotificationDestiny extends DroidGap {
	private WebView wv;
	private ProgressDialog pd;
	private Handler handler;
	@Override
	public void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.destiny);
		init();//ִ�г�ʼ������
		wv=(WebView)findViewById(R.id.webView);
		Intent intent=getIntent();
		String t=intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_TITLE);
		String url=intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT);
		
		Log.i("���",t);Log.i("����",url);
		setTitle(t);
		loadurl(wv,url);
        handler=new Handler(){
        	public void handleMessage(Message msg)
    	    {//����Handler�����������߳���UI��ͨѶ
    	      if (!Thread.currentThread().isInterrupted())
    	      {
    	        switch (msg.what)
    	        {
    	        case 0:
    	        	pd.show();        	
    	        	break;
    	        case 1:
    	        	pd.hide();//���ؽ��ȶԻ��򣬲���ʹ��dismiss()��cancel(),�����ٴε���show()ʱ����ʾ�ĶԻ���СԲȦ���ᶯ��
    	        	break;
    	        }
    	      }
    	      super.handleMessage(msg);
    	    }
        };
//		wv.loadUrl(url);
//		super.loadUrl(url);
	};
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {//��׽���ؼ�
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {   
            wv.goBack();   
            return true;   
        }else if(keyCode == KeyEvent.KEYCODE_BACK){
        	ConfirmExit();//���˷��ؼ������Ѿ����ܷ��أ���ִ���˳�ȷ��
        	return true; 
        }   
        return super.onKeyDown(keyCode, event);   
    }
    public void ConfirmExit(){//�˳�ȷ��
    	AlertDialog.Builder ad=new AlertDialog.Builder(NotificationDestiny.this);
    	ad.setTitle("�˳�");
    	ad.setMessage("�Ƿ��˳�?");
    	ad.setPositiveButton("��", new DialogInterface.OnClickListener() {//�˳���ť
			@Override
			public void onClick(DialogInterface dialog, int i) {
				// TODO Auto-generated method stub
				NotificationDestiny.this.finish();//�ر�activity
 
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
	
    @SuppressLint("SetJavaScriptEnabled")
	public void init(){//��ʼ��
    	wv=(WebView)findViewById(R.id.webView);
        wv.getSettings().setJavaScriptEnabled(true);//����JS
        wv.setScrollBarStyle(0);//���������Ϊ0�������������ռ䣬��������ҳ��
        wv.setWebViewClient(new WebViewClient(){   
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
            	loadurl(view,url);//������ҳ
                return true;   
            }//��д�������,��webview����
 
        });
        wv.setWebChromeClient(new WebChromeClient(){
        	public void onProgressChanged(WebView view,int progress){//������ȸı������ 
             	if(progress==100){
            		handler.sendEmptyMessage(1);//��ȫ������,����progressbar
            	}   
                super.onProgressChanged(view, progress);   
            }   
        });
 
    	pd=new ProgressDialog(NotificationDestiny.this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("���������У����Ժ�...");
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
