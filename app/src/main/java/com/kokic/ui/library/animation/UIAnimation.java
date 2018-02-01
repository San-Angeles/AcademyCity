package com.kokic.ui.library.animation;

import android.animation.*;
import android.content.*;
import android.view.animation.*;

import com.kokic.ui.library.animation.implement.*;
import com.kokic.ui.library.util.*;
import com.kokic.ui.library.view.*;
import com.kokic.ui.library.view.implement.*;

public class UIAnimation extends Painter implements UIListener, TickAnimation
{
	private TickAnimation.AnimationListener animationListener = null;
	
	private boolean isDynamic;
	
	public UIAnimation(Context context) {
		super(context);
		INTERVAL_TIME = DEFAULT_INTERVAL_TIME;
		setBackgroundColor(ColorFactory.TRANSPARENT);
		if (isDynamic)
			initAnimation();
	}

	public void setDynamic(boolean isDynamic) {
		this.isDynamic = isDynamic;
	}

	public boolean isDynamic() {
		return isDynamic;
	}

	@Override
	public void initAnimation() {
		ValueAnimator valueAnimator = createValueAnimator(ANIMATION_TIME);
			valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				UIAnimation.this.onAnimationUpdate(animation);
				if (animationListener != null) {
					animationListener.onAnimationUpdate(UIAnimation.this);
				}
			}
		});
		valueAnimator.start();
	}
	
	public void onAnimationUpdate(ValueAnimator animation) {}
	
	@Override
	public void setOnUpdateListener(TickAnimation.AnimationListener listener) {
		this.animationListener = listener;
	}
	
	public static ValueAnimator createValueAnimator(int time) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1.1f);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
		
		return valueAnimator;
    }
}
