package com.sanangeles.academycity;

import android.app.*;
import com.kokic.ui.library.platform.*;
import com.kokic.ui.library.util.*;
import com.sanangeles.academycity.event.*;
import com.sanangeles.academycity.interfaces.*;
import com.sanangeles.academycity.kit.entity.player.*;
import java.lang.reflect.*;

public final class Launcher
{
	public static Header header;
	
	public final void initialization(final Activity Context, InvocationHandler handler)
	{
		GameData.MinecraftActivity = Context;
		GameData.mDisplayMetrics = Context.getResources().getDisplayMetrics();
		GameData.mBitmapUtils = new BitmapUtils(Context);
		
		header = (Header)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Header.class}, handler);
		
		(GameData.initializationThread = new Thread(
			new Runnable() {
				public void run() {
					GameData.currentLanguage = header.evaluate("ModPE.getLanguage()").toString();
					GameData.currentMinecraftVersion = header.evaluate("ModPE.getMinecraftVersion()").toString();
				}
			}
		)).start();
		
		InventoryScreen.initialization();
		
		GameData.mGameWindow = new BaseFloat(Context);
		
	}
	
	public final void attackHook(long attacker, long victim)
	{}

	public final void chatHook(String str)
	{}

	public final void continueDestroyBlock(int x, int y, int z, int side, int progress)
	{}

	public final void destroyBlock(int x, int y, int z, int side)
	{}

	public final void projectileHitEntityHook(long projectile, long targetEntity)
	{}

	public final void eatHook(int hearts, Object saturationRatio)
	{}

	public final void entityAddedHook(long entity)
	{}

	public final void entityHurtHook(long attacker, long victim, int halfhearts)
	{}

	public final void entityRemovedHook(long entity)
	{}

	public final void explodeHook(long entity, int x, int y, int z, int power, boolean onFire)
	{}

	public final void leaveGame()
	{}

	public final void serverMessageReceiveHook(String str)
	{}

	public final void deathHook(long attacker, long victim)
	{}

	public final void playerAddExpHook(long player, int experienceAdded)
	{}

	public final void playerExpLevelChangeHook(long player, int levelsAdded)
	{}

	public final void redstoneUpdateHook(int x, int y, int z, int newCurrent, boolean inPlace, int blockId, int blockData)
	{}

	public final void screenChangeHook(String screenName) {
		switch (GameData.currentScreenName = screenName) {
			case InventoryScreen.screenName1:
				InventoryScreen.onInventoryScreen(0);
			break;
			case InventoryScreen.screenName2:
				InventoryScreen.onInventoryScreen(1);
			break;
			
			default: {
				InventoryScreen.elseScreen();
			}
		}
	}

	public final void newLevel() {
		GameData.player = new EntityWrapper(Launcher.header.evaluate("getPlayerEnt()"));
	}

	public final void selectLevelHook()
	{}

	public final void startDestroyBlock(int x, int y, int z, int side)
	{}

	public final void projectileHitBlockHook(long projectile, int blockX, int blockY, int blockZ, int side)
	{}

	public final void modTick()
	{}

	public final void useItem(int x, int y, int z, int itemid, int blockid, int side, int itemDamage, int blockDamage)
	{}
	
}
