package com.kokic.ui.library.view.implement;

import android.content.*;
import android.view.*;
import android.view.ViewGroup.*;

public final abstract interface UIListener
{
	public static final int top = Gravity.TOP;
	public static final int bottom = Gravity.BOTTOM;
	public static final int left = Gravity.LEFT;
	public static final int right = Gravity.RIGHT;
	public static final int center = Gravity.CENTER;

	public int width;
	public int height;

	public ViewTreeObserver mObserver;
	public LayoutParams mParams;
}
