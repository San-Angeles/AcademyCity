package com.sanangeles.academycity;

import android.app.*;
import com.kokic.ui.library.util.*;
import com.sanangeles.academycity.interfaces.*;
import java.lang.reflect.*;
import com.sanangeles.academycity.kit.*;

/* 用于初始化和modpe沟通的Class */
public class Launcher
{
	/* 用来实现java call js 的接口 */
	public static Header header;
	
	/* 初始化的方法 */
	public void initialization(final Activity Context, InvocationHandler handler)
	{
		/* 对Context进行赋值， MinecraftActivity即为游戏界面的Activty */
		GameData.MinecraftActivity = Context;
		/* 一些屏幕密度信息，便于获取宽高像素 */
		GameData.mDisplayMetrics = Context.getResources().getDisplayMetrics();
		/* 位图绘制工具 */
		GameData.mBitmapUtils = new BitmapUtils(Context);
		
		/* 通过反射使接口生效 */
		header = (Header)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Header.class}, handler);
		
		/* 游戏内部线程，处理一些常规操作 */
		(GameData.initializationThread = new Thread(
			new Runnable() {
				public void run() {
					/* 通过modpe获取当前语言类型 */
					GameData.currentLanguage = header.evaluate("ModPE.getLanguage()").toString();
					/* 通过modpe获取当前游戏版本号 */
					GameData.currentMinecraftVersion = header.evaluate("ModPE.getMinecraftVersion()").toString();
					
				}
			}
		)).start();
		
	}
	
	/* 启动器提供的事件Callback */
	
	public void attackHook(long attacker, long victim)
	{}

	public void chatHook(String str)
	{}

	public void continueDestroyBlock(int x, int y, int z, int side, int progress)
	{}

	public void destroyBlock(int x, int y, int z, int side)
	{}

	public void projectileHitEntityHook(long projectile, long targetEntity)
	{}

	public void eatHook(int hearts, Object saturationRatio)
	{}

	public void entityAddedHook(long entity)
	{}

	public void entityHurtHook(long attacker, long victim, int halfhearts)
	{}

	public void entityRemovedHook(long entity)
	{}

	public void explodeHook(long entity, int x, int y, int z, int power, boolean onFire)
	{}

	public void leaveGame()
	{}

	public void serverMessageReceiveHook(String str)
	{}

	public void deathHook(long attacker, long victim)
	{}

	public void playerAddExpHook(long player, int experienceAdded)
	{}

	public void playerExpLevelChangeHook(long player, int levelsAdded)
	{}

	public void redstoneUpdateHook(int x, int y, int z, int newCurrent, boolean inPlace, int blockId, int blockData)
	{}

	public void screenChangeHook(String screenName) {
		GameData.currentScreenName = screenName;
	}

	public void newLevel()
	{}

	public void selectLevelHook()
	{}

	public void startDestroyBlock(int x, int y, int z, int side)
	{}

	public void projectileHitBlockHook(long projectile, int blockX, int blockY, int blockZ, int side)
	{}

	public void modTick()
	{}

	public void useItem(int x, int y, int z, int itemid, int blockid, int side, int itemDamage, int blockDamage)
	{}
	
}
