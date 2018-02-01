package com.sanangeles.academycity.gui;

import android.graphics.*;
import android.view.*;
import android.view.WindowManager.*;
import android.widget.*;
import com.sanangeles.academycity.*;

public class WindowService
{
	private boolean isUsing = false;
	
	private ViewGroup layout;
	
	private static WindowManager mWindowManager;

	public boolean isUsing() {
		return isUsing;
	}

	public static WindowManager getWindowManager() {
		mWindowManager = (WindowManager)GameData.MinecraftActivity.getApplicationContext().getSystemService(GameData.MinecraftActivity.getApplicationContext().WINDOW_SERVICE);
		return mWindowManager;
	}

	static {
		getWindowManager();
	}

	private LayoutParams params;

	public WindowService(ViewGroup custom, int width,int height) {
		layout = custom;
		layout.setBackgroundColor(Color.WHITE);

		params = new LayoutParams();
		params.type = LayoutParams.TYPE_PHONE;
		params.format = PixelFormat.RGBA_8888; 
		params.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
		//LayoutParams.FLAG_ALT_FOCUSABLE_IM;

		params.width = width;  
		params.height = height;
	}

	public WindowService(int width,int height) {
		layout = new AbsoluteLayout(GameData.MinecraftActivity);
		layout.setBackgroundColor(Color.WHITE);

		params = new LayoutParams();
		params.type = LayoutParams.TYPE_PHONE;
		params.format = PixelFormat.RGBA_8888; 
		params.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
		//LayoutParams.FLAG_ALT_FOCUSABLE_IM;
		
		params.width = width;  
		params.height = height;
	}
	
	public void dismiss() {
		mWindowManager.removeView(layout);
		this.isUsing = false;
	}

	public void display(int gravity) {
		display(gravity, 0, 0);
	}

	public void display(int gravity, int x, int y) {
		params.gravity = (gravity == -1 ? (Gravity.LEFT | Gravity.TOP) : gravity);
		params.x = x;  
		params.y = y;
		if (!this.isUsing)
			mWindowManager.addView(layout, params);
		this.isUsing = true;
	}

	public void push_back(View v) {
		this.layout.addView(v, this.layout.getWidth(), this.layout.getHeight());
	}

	public void push_back(View v, int width, int height) {
		this.layout.addView(v, width, height);
	}

	public void push_back(View v, int width, int height, int x, int y) {
		v.setX(x);
		v.setY(y);
		this.layout.addView(v, width, height);
	}

	public ViewGroup getLayout() {
		return layout;
	}
	
}
