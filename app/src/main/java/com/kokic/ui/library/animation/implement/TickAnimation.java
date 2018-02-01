package com.kokic.ui.library.animation.implement;

import android.animation.*;
import com.kokic.ui.library.animation.*;

public final abstract interface TickAnimation
{
	public static final int ANIMATION_TIME = 1200;
	public static final int DEFAULT_INTERVAL_TIME = 400;
	
	int INTERVAL_TIME;
	
	public void initAnimation()
	public void setOnUpdateListener(AnimationListener listener)
	
	public static final abstract interface AnimationListener
	{
		public void onAnimationUpdate(UIAnimation ui)
	}
}
