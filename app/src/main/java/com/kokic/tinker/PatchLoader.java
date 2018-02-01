package com.kokic.tinker;

import android.app.*;
import android.os.*;
import com.kokic.tinker.interfaces.*;
import dalvik.system.*;
import java.io.*;
import java.util.*;

public class PatchLoader
{
	public static final class Struct
	{
		public static String phone_sdcard_path = Environment.getExternalStorageDirectory().getAbsolutePath();
		public static String phone_system_path = null;
		
		public static File app_temp_path = null;
		
		public static String patch_static_path = phone_sdcard_path + "/.patch_libs";
		public static String patch_system_path = null;
		public static String patch_system_dirs = "patch_dlf";
		
		public static String patch_elf_type = ".so";
		public static String patch_dex_type = ".dex";
		public static String patch_jar_type = ".jar";
	}

	public static final class PluginStruct
	{
		static final String plugin_static_path = Struct.patch_static_path;
		static final String plugin_system_path = "patch_dex";
		
		public static final String plugin_base_class_name = "Plugin.Main";
		
		public static File app_temp_path = null;
		
		String plugin_package_name;
		String plugin_class_name;

		String plugin_method_name;
	}
	
	public static class PluginData
	{
		public static String index = ".index";
		
		public static final String index_1 = "BasePluginListener";
		public static final String index_2 = "BaseActivityListener";
		
		String plugin_name;
		String plugin_listener;
		
		File plugin_data_file;
		
		PluginData(File plugin) {
			plugin_name = plugin.getName().split(Struct.patch_dex_type)[0];
			plugin_listener = getPluginListener(plugin);
			plugin_data_file = new File(plugin.getName() + index + getPluginLevel(plugin_listener));
		}
		
		public static int getPluginLevel(String listener) {
			switch(listener) {
				case index_1: return 1;
				case index_2: return 2;
				default: return 1;
			}
		}
		
		public static String getPluginListener(File file) {
			if (new File(file.getAbsolutePath() + index + 1).exists())
				return index_1;
			if (new File(file.getAbsolutePath() + index + 2).exists())
				return index_2;
			else return index_1;
		}
	}
	
	public static Class getDexClass(Activity Context, String basePath, String class_name)
	{
		Class _class = null;
		if (new File(basePath).exists()) {
			DexClassLoader loader = new dalvik.system.DexClassLoader(basePath,Context.getDir(PluginStruct.plugin_system_path,0).getAbsolutePath(), null, Context.getClass().getClassLoader());
			try {
				_class = loader.loadClass(class_name);
			} catch (ClassNotFoundException e) {}
		}
		return _class;
	}

	public static Object getObjectClass(Activity Context, String basePath, String class_name)
	{
		Object plugin = null;
		try {
			plugin = getDexClass(Context, basePath, class_name).newInstance();
		}
		catch (IllegalAccessException e) {}
		catch (InstantiationException e) {}

		return plugin;
	}
	
	public static File list_mkdir;
	public static File[] list_files;
	
	public static ArrayList<File> elf_patchs = new ArrayList<>();
	public static File[] elf_patchs_abs;
	
	public static ArrayList<File> dex_patchs = new ArrayList<>();
	public static File[] dex_patchs_abs;
	
	public static List<BaseActivityListener> activityPlugin = new ArrayList<>();
	
	public static void ActivityOnCreate(android.os.Bundle savedInstanceState, android.os.PersistableBundle persistentState) {
		for (BaseActivityListener plugin : activityPlugin) {
			plugin.onCreate(savedInstanceState, persistentState);
		}
	}
	
	public static void ActivityOnWindowFocusChanged(boolean hasFocus) {
		for (BaseActivityListener plugin : activityPlugin) {
			plugin.onWindowFocusChanged(hasFocus);
		}
	}
	

	public static boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		for (BaseActivityListener plugin : activityPlugin) {
			return plugin.onKeyDown(keyCode, event);
		} return false;
	}
	
	public static void _loadLibFile(File file) {
		try {
			System.load(file.getAbsolutePath());
		} catch (Exception e) {}
	}
	
	public static void _loadBaseFile(Activity act,File file) {
		((BasePluginListener)getObjectClass(act, file.getAbsolutePath(), PluginStruct.plugin_base_class_name))._init(act);
	}
	
	public static void _loadBaseFile(Activity act,File file, int level) {
		switch(level) {
			case 1:
				((BasePluginListener)getObjectClass(act, file.getAbsolutePath(), PluginStruct.plugin_base_class_name))._init(act);
			break;
			case 2: {
				BaseActivityListener plugin = (BaseActivityListener)getObjectClass(act, file.getAbsolutePath(), PluginStruct.plugin_base_class_name);
				activityPlugin.add(plugin);
				plugin._init(act);
			}
			break;
			default: return;
		}
	}
	
	public static void init(Activity Context)
	{
		Struct.phone_system_path = Context.getPackageResourcePath();
		Struct.app_temp_path =  Context.getDir(Struct.patch_system_dirs, 0);
		PluginStruct.app_temp_path =  Context.getDir(PluginStruct.plugin_system_path, 0);
		Struct.patch_system_path = Struct.app_temp_path.getAbsolutePath();
		
		elf_patchs_abs = Struct.app_temp_path.listFiles();
		dex_patchs_abs = Struct.app_temp_path.listFiles();
		
		list_mkdir = new File(Struct.patch_static_path);
		list_files = list_mkdir.listFiles();
		
		if (elf_patchs_abs.length != 0) {
			for (File file : elf_patchs_abs) if (isElfFile(file)) file.delete();
		}
		
		if (dex_patchs_abs.length != 0) {
			for (File file : dex_patchs_abs) if (isDexFile(file)) file.delete();
		}
		
		if (list_mkdir.exists()) {
			for (File file : list_files) {
				if (isElfFile(file)) {
					elf_patchs.add(file);
					try {
						copyFile(file);
					} catch (IOException e) {}
				}
				else if (isDexFile(file)) {
					_loadBaseFile(Context, file, PluginData.getPluginLevel(PluginData.getPluginListener(file)));
				}
			}
		}
		
		elf_patchs_abs = Struct.app_temp_path.listFiles();
		dex_patchs_abs = Struct.app_temp_path.listFiles();
		
	}
	
	public static boolean isElfFile(File file) {
		if (file.getName().endsWith(Struct.patch_elf_type))
			return true;
		else return false;
	}
	
	public static boolean isDexFile(File file) {
		if (file.getName().endsWith(Struct.patch_dex_type))
			return true;
		else return false;
	}
	
	public static File copyFile(File file) throws IOException
	{
		File saveAs = new File(Struct.app_temp_path, file.getName());
		saveAs.createNewFile();
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(saveAs);
		byte[] buffer = new byte[4096];
		int len;
		while ((len = fis.read(buffer)) != -1)
			fos.write(buffer, 0, len);
		fis.close();
		fos.close();
		return saveAs;
	}
}
