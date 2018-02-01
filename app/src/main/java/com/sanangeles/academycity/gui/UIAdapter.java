package com.sanangeles.academycity.gui;

import com.sanangeles.academycity.*;
import android.view.*;

public class UIAdapter
{
	public static float width(float width) {
		if (GameData.mDisplayMetrics != null)
			return (GameData.mDisplayMetrics.widthPixels/1280)*width;
		return width;
	}
	
	public static float height(float height) {
		if (GameData.mDisplayMetrics != null)
			return (GameData.mDisplayMetrics.widthPixels/720)*height;
		return height;
	}
	
	public static View getDecorView() {
		if (GameData.MinecraftActivity != null)
			return GameData.MinecraftActivity.getWindow().getDecorView();
		else return null;
	}
}
