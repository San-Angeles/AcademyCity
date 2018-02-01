package com.kokic.tinker.interfaces;

public abstract interface IPluginer
{
	public void _init(android.app.Activity act);

	public void onMinecraftLaunch(android.app.Activity act);

	public String getPluginName();
}
