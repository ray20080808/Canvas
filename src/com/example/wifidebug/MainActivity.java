package com.example.wifidebug;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pobear.widget.AppTitle;

public class MainActivity extends Activity{

	private TextView tvTitle;
	private View layout;
	private boolean isShow;
	private AppTitle appTitle;
	boolean pointDown,pointUp;
	
	private SparseArray<String> mBitmaoArray = new SparseArray<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);
		mBitmaoArray.put(10, "heool");
		System.out.println(mBitmaoArray.indexOfKey(10));
		
//		System.out.println(getResources().getDisplayMetrics().widthPixels+"  "+getResources().getDisplayMetrics().heightPixels);
		
	}

	private void initView() {
		TextView tvText = (TextView) findViewById(R.id.tvText);
		tvText.setMovementMethod(ScrollingMovementMethod.getInstance());
		appTitle = (AppTitle) findViewById(R.id.appTitle);
		
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		layout = (View) findViewById(R.id.textLayout);
		
		
		ImageView imageView = (ImageView) findViewById(R.id.imgView);
		imageView.setOnTouchListener(new OnTouchListener() {
			
//			单手指操作：ACTION_DOWN---ACTION_MOVE----ACTION_UP
//			多手指操作：ACTION_DOWN---ACTION_POINTER_DOWN---ACTION_MOVE--ACTION_POINTER_UP---ACTION_UP
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				switch (event.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_DOWN:
					System.out.println(" ACTION_DOWN ");
					break;
				case MotionEvent.ACTION_POINTER_DOWN:
					System.out.println(" ACTION_POINTER_DOWN ");
					pointDown = true;
					break;
					
				case MotionEvent.ACTION_MOVE: 
					System.out.println(" ACTION_MOVE ");
					break;

				case MotionEvent.ACTION_POINTER_UP: 
					System.out.println(" ACTION_POINTER_UP ");
					pointUp = true;
					break;
					
				case MotionEvent.ACTION_UP: 
					System.out.println(" ACTION_UP ");
					if(pointDown && pointUp){
						System.out.println(pointDown+" "+pointUp);
						pointDown = false;
						pointUp = false;
						return true;
					}
					break;
				}
				
				return false;

			}
		});
		
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!isShow){
					appTitle.setVisibility(View.GONE);
					layout.setVisibility(View.GONE);
					isShow = true;
				}else{
					appTitle.setVisibility(View.VISIBLE);
					layout.setVisibility(View.VISIBLE);
					isShow = false;
				}
			}
		});
	}

	private void setIp() {
		TextView tvIp=(TextView) findViewById(R.id.tvIp);
		tvIp.setText(getIp());

		try {
			Runtime run=Runtime.getRuntime();
			run.exec("su");
			run.exec("setprop service.adb.tcp.port");
			run.exec("stop adbd");
			run.exec("start adbd");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 private String getIp(){
	     WifiManager wm=(WifiManager)this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
	     //检查Wifi状态  
	     if(!wm.isWifiEnabled())
	         wm.setWifiEnabled(true);
	     WifiInfo wi=wm.getConnectionInfo();
	     //获取32位整型IP地址  
	     int ipAdd=wi.getIpAddress();
	     //把整型地址转换成“*.*.*.*”地址  
	     String ip=intToIp(ipAdd);
	     return ip;
	 }
	 private String intToIp(int i) {
	     return (i & 0xFF ) + "." +
	     ((i >> 8 ) & 0xFF) + "." +
	     ((i >> 16 ) & 0xFF) + "." +
	     ( i >> 24 & 0xFF) ;
	 } 

}
