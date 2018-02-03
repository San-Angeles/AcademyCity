package com.sanangeles.academycity;

import java.io.*;

public final class Runner
{
	public final static String add(Object... params) {
		StringBuilder mStringBuilder = new StringBuilder(params[0]);
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
