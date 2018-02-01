package com.kokic.ui.library.platform;

import android.app.*;
import java.util.*;
import com.kokic.ui.library.util.*;
import android.widget.*;
import android.view.*;
import android.os.*;
import com.kokic.ui.library.platform.framework.control.*;

public class BaseConfig extends Activity
{
	private BaseScreen mBackground;
	private List<BaseScreen> Children = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UIUtil.init(this);
		mBackground = new BaseScreen(this);
		mBackground.setBackgroundColor(ColorFactory.BACKGROUND_DARK);
		super.setContentView(mBackground);
	}
	
	public void push(BaseControl control) {}
	
	public void push(View view, int x, int y) {
		mBackground.addView(view, new AbsoluteLayout.LayoutParams(view.getWidth(), view.getHeight(), x, y));
	}
	
	public void push(View view, int x, int y, int width, int height) {
		mBackground.addView(view, new AbsoluteLayout.LayoutParams(width, height, x, y));
	}
	
	public BaseScreen[] getChildren() {
		return (BaseScreen[])Children.toArray();
	}
}
