package com.sanangeles.academycity.gui;

import com.sanangeles.academycity.*;
import android.view.*;

public final class UIAdapter
{
	public static int dip2px(float dpValue) {
		float scale = GameData.MinecraftActivity.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public final static int sp2px(float spValue) {
		final float fontScale = GameData.MinecraftActivity.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	public final static int px2dip(float pxValue) {
		float scale = GameData.MinecraftActivity.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public final static int px2sp(float pxValue) {
		final float fontScale = GameData.MinecraftActivity.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}
	
	public final static View getDecorView() {
		if (GameData.MinecraftActivity != null)
			return GameData.MinecraftActivity.getWindow().getDecorView();
		else return null;
	}
}
