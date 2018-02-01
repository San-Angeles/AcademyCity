package com.kokic.ui.library.animation;

import android.animation.*;
import android.content.*;
import android.graphics.*;
import com.kokic.ui.library.util.*;
import com.kokic.ui.library.view.*;

public class UIUpdater extends UIAnimation
{
	public int UPDATER_SIZE = 3;
	public int Min;
	public int widthStack = 1;
	public int heiegtStack = 1;
	public int[] widthUpdater = {
		0, Min, Min * 2
	};
	public int[] heightUpdater = {
		0, Min, Min * 2
	};
	
	public UIUpdater(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO: Implement this method
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int val_1 = getMeasuredWidth();
		int val_2 = getMeasuredHeight();
		Min = val_1 > val_2 ? val_2/5 : val_1/5;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		onUpdateData();
		onRender();
	}
	
	protected void onRender() {
		setPaintColor(ColorFactory.PROGRESSBAR_DEFAULT);
		UIRenderer.rectangle(this, widthUpdater[0], 0, Min, Min);
		UIRenderer.rectangle(this, widthUpdater[1], 0, Min, Min);
		UIRenderer.rectangle(this, widthUpdater[2], 0, Min, Min);
	}
	
	protected void onUpdateData() {
		
		for(int i = 0; i < UPDATER_SIZE; ++i) {
			widthUpdater[i] += widthStack * (i + 1);	
			if (widthUpdater[i] >= width)
				widthUpdater[i] = 0;
			
			heightUpdater[i] += heiegtStack * (i + 1);
			if (heightUpdater[i] >= height)
				heightUpdater[i] = 0;
		}
		
		invalidate();
	}
}
