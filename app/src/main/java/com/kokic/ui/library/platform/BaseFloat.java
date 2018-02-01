package com.kokic.ui.library.platform;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.view.*;
import android.widget.*;
import java.io.*;

public class BaseFloat
{
	protected Activity context;
	protected PopupWindow window;
	protected LinearLayout layout;

	protected boolean isUsing = false;
	
	public BaseFloat(Activity Context, int width, int height)
	{
		context = Context;
		window = new PopupWindow(width, height);
		layout = new LinearLayout(Context);
		layout.setBackgroundColor(Color.WHITE);
		window.setContentView(layout);
	}

	public BaseFloat(Activity Context) {
		this(Context, Context.getResources().getDisplayMetrics().widthPixels, Context.getResources().getDisplayMetrics().heightPixels);
	}

	public boolean isUsing() {
		return isUsing;
	}

	public void setbackground(Bitmap bmp) {
		this.layout.setBackground(new BitmapDrawable(bmp));
	}

	public void setbackground(Drawable draw) {
		this.layout.setBackground(draw);
	}

	public void setbackground(String path) throws IOException {
		this.layout.setBackground(new BitmapDrawable(BitmapFactory.decodeStream(this.context.getAssets().open(path))));
	}

	public static Bitmap getAssetBitmap(Activity context, String path) {
		try {
			return BitmapFactory.decodeStream(context.getAssets().open(path));
		} catch (IOException e) {}
		return null;
	}

	public void full() {
		window.setWidth(context.getResources().getDisplayMetrics().widthPixels);
		window.setHeight(context.getResources().getDisplayMetrics().heightPixels);
	}

	public void push(View v) {
		this.layout.addView(v);
	}

	public void push(View v, int width, int height) {
		this.layout.addView(v, width, height);
	}

	public void setTouchable(boolean able) {
		this.window.setTouchable(able);
	}

	public void dismiss() {
		this.isUsing = false;
		this.window.dismiss();
	}

	public void show() {
		this.show(Gravity.CENTER | Gravity.CENTER, 0, 0);
	}

	public void show(final int arg2,final int arg4)  {
		final PopupWindow tag = this.window;
		this.isUsing = true;
		context.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				tag.showAtLocation(context.getWindow().getDecorView(),Gravity.LEFT|Gravity.TOP,arg2,arg4);
			}
		});
	}

	public void show(final int gravity,final int x,final int y) {
		final PopupWindow tag = this.window;
		this.isUsing = true;
		context.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				tag.showAtLocation(context.getWindow().getDecorView(), gravity, x, y);
			}
		});
	}

	public PopupWindow getWindow() {
		return window;
	}

	public LinearLayout getLayout() {
		return layout;
	}
}


