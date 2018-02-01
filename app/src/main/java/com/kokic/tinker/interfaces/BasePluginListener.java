package com.kokic.tinker.interfaces;

public abstract interface BasePluginListener
{
	public void _init(android.app.Activity act);
	
	public String getPluginName();
}
