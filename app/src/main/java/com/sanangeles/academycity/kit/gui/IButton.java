package com.sanangeles.academycity.kit.gui;

import android.graphics.*;
import android.graphics.drawable.*;
import android.view.*;
import android.widget.*;
import com.sanangeles.academycity.*;
import android.view.View.*;

public class IButton extends Button
{
	private int width;
	private int height;
	private Drawable background1;
	private Drawable background2;
	
	private OnClickListener clickListener = null;
	
	public IButton(int width, int height) {
		super(GameData.MinecraftActivity);
		setWidth(width);
		setHeight(height);
		
		super.setOnClickListener(
			new OnClickListener() {
				@Override public void onClick(View p1) {
					Launcher.header.evaluate("Level.playSoundEnt(Player.getEntity(),\"random.click\",1,1)");
					if (clickListener != null)
						clickListener.onClick(p1);
				}
			}
		);
	}

	@Override
	public void setWidth(int pixels) {
		super.setWidth(this.width = pixels);
	}

	@Override
	public void setHeight(int pixels) {
		super.setHeight(this.height = pixels);
	}

	@Override
	public void setOnClickListener(View.OnClickListener l) {
		this.clickListener = l;
	}

	@Override
	public void setOnTouchListener(View.OnTouchListener l)
	{}
	
	public void setOnTouchCallback(final View.OnTouchListener Callback, Drawable up, Drawable down) {
		background1 = up;//GameData.mBitmapUtils.buildDrawable(GameData.getTexture("gui/newgui/buttons/border/base.png"), 0, 0, 7, 7, 2, 2, 3, 3, width, height);
		background2 = down;//GameData.mBitmapUtils.buildDrawable(GameData.getTexture("gui/newgui/buttons/border/basePress.png.png"), 0, 0, 7, 7, 2, 2, 3, 3, width, height);
		setBackgroundDrawable(background1);

		super.setOnTouchListener(
			new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						setBackground(background1);
						setTextColor(Color.BLACK);
					}
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						setBackground(background2);
						setTextColor(Color.WHITE);
					}
					Callback.onTouch(v, event);
					return false;
				}
			}
		);
		
	}
}
