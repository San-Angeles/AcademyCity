package com.sanangeles.academycity.gui;

import android.view.*;
import android.widget.*;
import com.sanangeles.academycity.*;

public class Popuper
{
	private final PopupWindow mPopupWindow;
	private final View mView;
	
	public Popuper(View content, int width, int height) {
		mPopupWindow = new PopupWindow(mView = content, width, height);
	}
	
	public void show() {
		this.show(Gravity.CENTER | Gravity.CENTER, 0, 0);
	}

	public void show(final int arg2, final int arg4)  {
		GameData.MinecraftActivity.runOnUiThread(
			new Runnable() {
				@Override
				public void run() {
					mPopupWindow.showAtLocation(UIAdapter.getDecorView(), Gravity.LEFT|Gravity.TOP, arg2, arg4);
				}
			}
		);
	}

	public void show(final int gravity, final int x, final int y) {
		GameData.MinecraftActivity.runOnUiThread(
			new Runnable() {
				@Override
				public void run() {
					mPopupWindow.showAtLocation(UIAdapter.getDecorView(), gravity, x, y);
				}
			}
		);
	}

	public PopupWindow getPopupWindow() {
		return mPopupWindow;
	}
}
