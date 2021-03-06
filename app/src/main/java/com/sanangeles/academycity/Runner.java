package com.sanangeles.academycity;

import java.io.*;
import android.widget.*;

public final class Runner
{
	public final static String CHAR_1 = "{";
	public final static String CHAR_2 = "}";
	public final static String CHAR_3 = "{}";
	public final static String CHAR_4 = "(";
	public final static String CHAR_5 = ")";
	public final static String CHAR_6 = "()";
	
	public final static String add(Object... params) {
		StringBuilder mStringBuilder = new StringBuilder((String)params[0]);
		for (int i = 1; i < params.length; ++i)
			mStringBuilder.append(params[i]);
		return mStringBuilder.toString();
	}
	
	public final static Object returnObject(String label) {
		return Launcher.header.evaluate("(function(){return " + label + "})()");
	}
	
	public final static Object evaluate(Object... params) {
		return Launcher.header.evaluate(add(params));
	}
	
	public final static String wrapper(String className, String name, Object... params) {
		StringBuilder mStringBuilder = new StringBuilder(className);
		mStringBuilder.append(name);
		mStringBuilder.append("(");
		for (int i = 0; i < params.length; ++i) {
			mStringBuilder.append(params[i]);
			if (i < params.length-1)
				mStringBuilder.append(",");
		}
		mStringBuilder.append(")");
		return mStringBuilder.toString();
	}
	
	public final static void print(final String messages) {
		print(messages, 0);
	}
	
	public final static void print(final String messages, final int t) {
		GameData.MinecraftActivity.runOnUiThread(
			new Runnable() {
				@Override
				public void run() {
					Toast.makeText(GameData.MinecraftActivity, messages, t).show();
				}
			}
		);
	}
	
	public final static void exitWorld() {
		evaluate(getClassName(), "leaveGame", CHAR_6);
	}
	
	public final static String reader(String filePath){
		String str = "";
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					str += lineTxt;
				}
				read.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public final static String getClassName() {
		return "ModPE.";
	}
}
