package com.kokic.ui.library.platform.framework.control;

import android.view.*;
import com.kokic.ui.library.platform.framework.*;
import android.os.*;

public class BaseContainer extends ViewGroup
{
	private final Framework framework;
	
	public BaseContainer(Framework context) {
		super(context);
		this.framework = context;
	}

	public void addView(BaseControl child) {
		// TODO: Implement this method
		super.addView(child, child.getWidth(), child.getHeight());
		child.getFramework().setContainer(this);
		child.isLoaded = true;
		child.onLoaded();
	}

	@Override
	protected void onLayout(boolean p1, int p2, int p3, int p4, int p5) {}
	
}
