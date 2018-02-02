package com.sanangeles.academycity;

import android.app.*;
import android.media.*;
import android.util.*;
import com.kokic.ui.library.platform.*;
import com.kokic.ui.library.util.*;

public class GameData
{
	public static Activity MinecraftActivity = null;
	
	public static DisplayMetrics mDisplayMetrics = null;
	
	public static BitmapUtils mBitmapUtils = null;
	
	public static Thread initializationThread = null;
	//public static Thread initializationPlugin = null;
	
	public static BaseFloat basicWindow = null;
	public static BaseFloat mGameWindow = null;
	
	public static String currentLanguage = null;
	
	public static String currentMinecraftVersion = null;
	
	public static String currentScreenName = null;
	
	public static MediaPlayer mMediaPlayer = new MediaPlayer();
	
	public static final String assetsPath = "resourcepacks/vanilla/client/textures/";
	
	public static String getTexture(String path) {
		return new StringBuffer(assetsPath).append(path).toString();
	}
}
