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


public class MainActivity extends Activity{

	private TextView tvTitle;
	private View layout;
	private boolean isShow;
	boolean pointDown,pointUp;
	
	private SparseArray<String> mBitmaoArray = new SparseArray<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mBitmaoArray.put(10, "heool");
		System.out.println(mBitmaoArray.indexOfKey(10));
		
//		System.out.println(getResources().getDisplayMetrics().widthPixels+"  "+getResources().getDisplayMetrics().heightPixels);
		
	}

}
