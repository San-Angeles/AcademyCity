package com.kokic.ui.library.platform;

import android.widget.*;
import android.view.*;
import android.content.*;

import com.kokic.ui.library.view.implement.*;
import com.kokic.ui.library.util.*;

public class BaseScreen extends AbsoluteLayout implements UIListener
{
	private final int screenId;
	
	public BaseScreen(Context context) {
		super(context);
		screenId = UIUtil.requestId();
		onCreate();
	}
	
    public BaseScreen(android.content.Context context, android.util.AttributeSet attrs) {
		super(context, attrs);
		screenId = UIUtil.requestId();
		onCreate();
	}

    public BaseScreen(android.content.Context context, android.util.AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		screenId = UIUtil.requestId();
		onCreate();
	}

    public BaseScreen(android.content.Context context, android.util.AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		screenId = UIUtil.requestId();
		onCreate();
	}
	
	private void onCreate() {}

	public int getScreenId() {
		return screenId;
	}
}
