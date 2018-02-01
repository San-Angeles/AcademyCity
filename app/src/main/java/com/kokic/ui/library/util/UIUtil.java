package com.kokic.ui.library.util;

import android.content.*;
import android.content.res.*;
import android.util.*;
import android.view.*;
import android.app.*;

public class UIUtil
{
	public static Context mContext;
	public static View mDecView;
	public static DisplayMetrics mDisplayMetrics;
	public static int screenWidth;
	public static int screenHeight;
	
	public static int dipWidth;
	public static int dipHeight;
	
	public static void init(Context context) {
		mContext = context;
		mDecView = ((Activity)context).getWindow().getDecorView();
		mDisplayMetrics = mContext.getResources().getDisplayMetrics();
		dipWidth = (screenWidth = mDisplayMetrics.widthPixels)/1280;
		dipHeight = (screenHeight = mDisplayMetrics.heightPixels)/720;
	}
	
	private static int UIID = 0;
	
	public static int requestId() {
		return ++UIID;
	}
	
	public int scaleWidth(int width) {
		return dipWidth * width;
	}
	
	public int scaleHeight(int height) {
		return dipHeight * height;
	}
	
    public static int dpToPx(float dp, Resources resources){
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }
	
}
