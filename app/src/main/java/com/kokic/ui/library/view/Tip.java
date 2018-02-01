package com.kokic.ui.library.view;

import android.widget.*;

public class Tip extends Toast
{
	public android.app.Activity ctx;

	public static final int LENGTH_LONGLONG = 2;

	public static final int LENGTH_LONG = 1;

	public static final int LENGTH_BASIC = 0;

    public static final int LENGTH_SHORT = 0;

	public static final int BASIC_COLOR = android.graphics.Color.parseColor("#CA373737");

	@Override
	public void show() {
		super.show();
	}

	public void show(String messages) {
		setText(messages);
		super.show();
	}

	@Override
    public void cancel() {
		super.cancel();
	}

	public static Tip makeText(android.content.Context context, java.lang.CharSequence text, int duration) {
		return new Tip(android.widget.Toast.makeText(context, text, duration));
	}

    public static Tip makeText(android.content.Context context, int resId, int duration) throws android.content.res.Resources.NotFoundException {
		return new Tip(android.widget.Toast.makeText(context, resId, duration));
	}

	@Override
    public void setView(android.view.View view) {
		super.setView(view);
	}

	public Tip setContent(android.view.View view) {
		super.setView(view);
		return this;
	}
	
	public android.view.View getBasicView() {
		return Toast.makeText(ctx,"",0).getView();
	}

	public void setX(int x) {
		this.getView().setX(x);
	}

	public void setY(int y) {
		this.getView().setY(y);
	}
	
	public Tip setColor(int color) {
		this.getView().setBackgroundColor(color);
		return this;
	}
	
	public void setBitmap(android.graphics.Bitmap image) {
		this.getView().setBackground(new android.graphics.drawable.BitmapDrawable(image));
	}

	public void setbackground(android.graphics.drawable.Drawable image) {
		this.getView().setBackground(image);
	}

	public android.graphics.drawable.Drawable getbackground() {
		return this.getView().getBackground();
	}

	public int getWidth() {
		return this.getView().getWidth();
	}

	public int getHeight() {
		return this.getView().getHeight();
	}

	@Override
    public android.view.View getView() {
		return super.getView();
	}

	@Override
    public void setDuration(int duration) {
		super.setDuration(duration);
	}

	@Override
    public int getDuration() {
		return super.getDuration();
	}

	@Override
    public void setMargin(float horizontalMargin, float verticalMargin) {
		setMargin(horizontalMargin, verticalMargin);
	}

	@Override
    public float getHorizontalMargin() {
		return super.getHorizontalMargin();
	}

	@Override
    public float getVerticalMargin() {
		return super.getVerticalMargin();
	}

	@Override
    public void setGravity(int gravity, int xOffset, int yOffset) {
		super.setGravity(gravity, xOffset, yOffset);
	}

	@Override
    public int getGravity() {
		return super.getGravity();
	}

	@Override
    public int getXOffset() {
		return super.getXOffset();
	}

	@Override
    public int getYOffset() {
		return super.getYOffset();
	}

    public Tip setMessages(int resId) {
		super.setText(resId);
		return this;
	}

    public Tip setMessages(java.lang.CharSequence s) {
		super.setText(s);
		return this;
	}

	@Override
    public Tip(android.content.Context context) {
		super(context);
		this.ctx = (android.app.Activity)context;
		this.setView(this.getBasicView());
		this.setColor(BASIC_COLOR);
	}
	
	@Override
    public Tip(android.widget.Toast print) {
		super(print.getView().getContext());
		this.ctx = (android.app.Activity)print.getView().getContext();
		this.setView(this.getBasicView());
		this.setColor(BASIC_COLOR);
	}
}

