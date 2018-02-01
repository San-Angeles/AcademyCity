package com.sanangeles.academycity;

import java.io.*;

public class Runner
{
	/* 调用这个方法并输入标签可以获取到BlockLauncher ModpeApi中的值 */
	public static Object returnObject(String label) {
		return Launcher.header.evaluate("(function(){return " + label + "})()");
	}
	
	public static String reader(String filePath){
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
}
