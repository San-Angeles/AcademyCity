package com.kokic.ui.library.util;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import java.io.*;

public class BitmapUtils
{
	public Activity Context;
	public BitmapUtils(Activity client) {
		Context = client;
	}
	
	public int dip(int pixels) {
		return (int)android.util.TypedValue.applyDimension(android.util.TypedValue.COMPLEX_UNIT_DIP,2,Context.getResources().getDisplayMetrics())*pixels; 
	}

	public void saveBitmap(Bitmap bitmap,String path) {
		File file = new java.io.File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+path);
		file.getParentFile().mkdirs();
		try {
			FileOutputStream fOut = new java.io.FileOutputStream(file);
			bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, fOut);
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {}
		} catch (FileNotFoundException e) {}
	}
	
	public static Drawable getDrawable(Bitmap bitmap) {
		return new android.graphics.drawable.BitmapDrawable(bitmap);
	}
	
	public Bitmap getFromTexture(final String direction) {
		try {
			return BitmapFactory.decodeStream(Context.getAssets().open(direction));
		} catch (IOException e) {
			return null;
		}
	}
	
	public static Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }
	
	public static Bitmap getTrimmed(Bitmap bitmap,int x,int y,int width,int height) {
		return android.graphics.Bitmap.createBitmap(bitmap,x,y,width,height);
	}
	
	public Bitmap getScaled(Bitmap bitmap,int scaleX,int scaleY) {
		return android.graphics.Bitmap.createScaledBitmap(bitmap,scaleX,scaleY,false);
	}
	
	public Bitmap ultimateBitmap(String direction,int x,int y,int width,int height,int scaleX,int scaleY) {
		return getScaled(getTrimmed(getFromTexture(direction),x,y,width,height),scaleX|dip(width),scaleY|dip(height));
	}
	
	public Drawable ultimateDrawable(String direction,int x,int y,int width,int height,int scaleX,int scaleY) {
		return getDrawable(getScaled(getTrimmed(getFromTexture(direction),x,y,width,height),scaleX|dip(width),scaleY|dip(height)));
	}
	
	public Drawable stretchImage(Bitmap bm,int x,int y,int stretchWidth,int stretchHeight,int width,int height) {
		Bitmap blank = android.graphics.Bitmap.createBitmap(width,height,android.graphics.Bitmap.Config.ARGB_8888);
		Bitmap part1 = Bitmap.createBitmap(bm,0,0,x,y);
		Bitmap part2 = Bitmap.createBitmap(bm,x,0,stretchWidth,y);
		Bitmap part3 = Bitmap.createBitmap(bm,x+stretchWidth,0,bm.getWidth()-x-stretchWidth,y);
		Bitmap part4 = Bitmap.createBitmap(bm,0,y,x,stretchHeight);
		Bitmap part5 = Bitmap.createBitmap(bm,x,y,stretchWidth,stretchHeight);
		Bitmap part6 = Bitmap.createBitmap(bm,x+stretchWidth,y,bm.getWidth()-x-stretchWidth,stretchHeight);
		Bitmap part7 = Bitmap.createBitmap(bm,0,y+stretchHeight,x,bm.getHeight()-y-stretchHeight);
		Bitmap part8 = Bitmap.createBitmap(bm,x,y+stretchHeight,stretchWidth,bm.getHeight()-y-stretchHeight);
		Bitmap part9 = Bitmap.createBitmap(bm,x+stretchWidth,y+stretchHeight,bm.getWidth()-x-stretchWidth,bm.getHeight()-y-stretchHeight);
		Canvas canvas = new android.graphics.Canvas(blank);
		canvas.drawBitmap(part1,0,0,null);
		canvas.drawBitmap(Bitmap.createScaledBitmap(part2,width-bm.getWidth()+stretchWidth,y,false),x,0,null);
		canvas.drawBitmap(part3,width-bm.getWidth()+stretchWidth+x,0,null);
		canvas.drawBitmap(Bitmap.createScaledBitmap(part4,x,height-bm.getHeight()+stretchHeight,false),0,y,null);
		canvas.drawBitmap(Bitmap.createScaledBitmap(part5,width-bm.getWidth()+stretchWidth,height-bm.getHeight()+stretchHeight,false),x,y,null);
		canvas.drawBitmap(Bitmap.createScaledBitmap(part6,part3.getWidth(),height-bm.getHeight()+stretchHeight,false),width-bm.getWidth()+stretchWidth+x,y,null);
		canvas.drawBitmap(part7,0,height-bm.getHeight()+stretchHeight+y,null);
		canvas.drawBitmap(Bitmap.createScaledBitmap(part8,width-bm.getWidth()+stretchWidth,part7.getHeight(),false),x,height-bm.getHeight()+stretchHeight+y,null);
		canvas.drawBitmap(part9,width-bm.getWidth()+stretchWidth+x,height-bm.getHeight()+stretchHeight+y,null);
		return new android.graphics.drawable.BitmapDrawable(blank);
	}

	public Drawable buildDrawable(String AssetPath, int xOffset, int yOffset, int width, int height, int xSize, int ySize, int stretchWidth, int stretchHeight, int _width, int _height) {
		return stretchImage(ultimateBitmap(AssetPath, xOffset, yOffset, width, height, 0, 0), dip(xSize), dip(ySize), dip(stretchWidth), dip(stretchHeight), _width, _height);
	}
}
