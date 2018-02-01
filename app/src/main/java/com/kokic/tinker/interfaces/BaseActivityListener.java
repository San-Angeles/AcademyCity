package com.kokic.tinker.interfaces;

import android.app.*;

public abstract interface BaseActivityListener
{
	public void _init(Activity act);
	
	public String getPluginName();
	
	public void onCreate(android.os.Bundle savedInstanceState, android.os.PersistableBundle persistentState);
	
	public void onWindowFocusChanged(boolean hasFocus);
	
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event);
}
