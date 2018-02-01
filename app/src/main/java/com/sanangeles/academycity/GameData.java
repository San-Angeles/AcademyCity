package com.sanangeles.academycity;

import android.app.*;
import android.util.*;
import com.kokic.ui.library.util.*;
import com.sanangeles.academycity.kit.*;

/* 用于提供有关游戏数据的Class */
public class GameData
{
	/* 游戏界面的Activty */
	public static Activity MinecraftActivity = null;
	/* 屏幕密度 */
	public static DisplayMetrics mDisplayMetrics = null;
	/* 位图绘制工具 */
	public static BitmapUtils mBitmapUtils = null;
	
	/* 游戏内部线程 */
	public static Thread initializationThread = null;
	//public static Thread initializationPlugin = null;
	/* tick(帧事件)，可以调整更新频率 */
	public static Ticker mGameTicker = null;
	
	/* 打开游戏时的语言类型 */
	public static String currentLanguage = null;
	/* 游戏版本号 */
	public static String currentMinecraftVersion = null;
	/* 当前屏幕名称，可以用于监听当前屏幕状态 */
	public static String currentScreenName = null;
	
	public static final String assetsPath = "resource_packs/vanilla/textures/";
	public static String getTexture(String path) {
		return new StringBuffer(assetsPath).append(path).toString();
	}
}
