package com.kokic.ui.library.platform.framework.control;

import android.view.*;
import com.kokic.ui.library.platform.framework.*;

public class BaseControl extends View
{
	private final Framework framework;
	
	protected boolean isLoaded;
	
	public BaseControl(Framework mContext) {
		super(mContext);
		this.framework = mContext;
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	public Framework getFramework() {
		return framework;
	}
	
	protected void onLoaded() {}
}
