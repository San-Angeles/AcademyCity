package com.kokic.ui.library.view;

import android.content.*;
import android.graphics.*;
import com.kokic.ui.library.view.implement.*;
import com.kokic.ui.library.util.*;

public class ProgressBar extends Painter
{
	public static final int default_width = 512;
	public static final int default_height = 34;
	public static final int default_frame_size = 2;

	private Context ProxyContext;
	private Style style;
	private int Color;
	private int frameColor;

	private int tickSpeed = 600;

	private int frameSize = 10;

	private Runnable tickEvent = null;

	private float widthTick = 1;
	private float heightTick = 1;

	private float progressWidth = 0;
	private float progressHeight = 0;

	private float progressWidthMax;
	private float progressHeightMax;

	private Runnable drawEvent = new Runnable() {
		public void run() {
			setPaintColor(frameColor);
			UIRenderer.frame(ProgressBar.this, 0, 0, width, height, frameSize);
			setPaintColor(Color);
			UIRenderer.rectangle(ProgressBar.this, frameSize, height-frameSize, progressWidth, -progressHeight);
		}
	};

	public ProgressBar(Context proxyContext, Style style) {
		this(proxyContext);
		this.style = style;
	}

	public void setTickEvent(Runnable tickEvent) {
		this.tickEvent = tickEvent;
	}

	public Runnable getTickEvent() {
		return tickEvent;
	}

	public void setTickSpeed(int tickSpeed) {
		this.tickSpeed = tickSpeed;
	}

	public int getTickSpeed() {
		return tickSpeed;
	}

	public void setFrameSize(int frameSize) {
		this.frameSize = frameSize;
	}

	public int getFrameSize() {
		return frameSize;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Style getStyle() {
		return style;
	}

	public void setTick(float tick) {
		this.widthTick = tick;
	}

	public static enum Style
	{
		LATERAL_THRUST,
		VERTICAL_THRUST,
		ELANTRA,
		CUSTOM
	}

	public ProgressBar(Context proxyContext, int color) {
		super(proxyContext);
		style = Style.LATERAL_THRUST;
		ProxyContext = proxyContext;
		Color = color;
		frameColor = ColorFactory.PROGRESSBAR_FRAME;

		postDelayed(new Runnable() {
			public void run() {
				if (tickEvent != null)
					tickEvent.run();
				postDelayed(this, tickSpeed);
			}
		}, tickSpeed);
	}

	public ProgressBar(Context proxyContext) {
		this(proxyContext, ColorFactory.PROGRESSBAR_DEFAULT);
	}

	@Override
	protected void onSizeChanged(int _width, int _height, int _oldwidth, int _oldheight) {
		// TODO: Implement this method
		super.onSizeChanged(_width, _height, _oldwidth, _oldheight);

		heightTick = ((progressHeightMax = height-frameSize*2) / (progressWidthMax = width-frameSize*2)) * widthTick;

		//Main.selfpoint.sentMessage("" + heightTick);
	}

	public int getProgress() {
		if (style == Style.LATERAL_THRUST)
			return (int)((progressWidth / progressWidthMax)*100);
		return (int)((progressHeight / progressHeightMax)*100);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (style == Style.LATERAL_THRUST) {
			progressHeight = height-frameSize*2;
			progressWidth += widthTick;
		}

		if (style == Style.VERTICAL_THRUST) {
			progressWidth = width-frameSize*2;
			progressHeight += heightTick;
		}

		if (style == Style.ELANTRA) {
			progressWidth += widthTick;
			progressHeight += heightTick;
		}

		if (progressWidth > progressWidthMax) {
			progressWidth = 0;
		}

		if (progressHeight > progressHeightMax) {
			progressHeight = 0;
		}

		drawEvent.run();
		//postDelayed(drawEvent, tickTime);
		invalidate();

		//Main.selfpoint.sentMessage(getProgress()+"%");
	}

}

