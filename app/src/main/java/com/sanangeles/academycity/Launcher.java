package com.sanangeles.academycity;

import android.app.*;
import android.os.*;
import com.kokic.ui.library.util.*;
import com.sanangeles.academycity.core.*;
import com.sanangeles.academycity.event.*;
import com.sanangeles.academycity.interfaces.*;
import com.sanangeles.academycity.kit.*;
import com.sanangeles.academycity.kit.entity.player.*;
import com.sanangeles.academycity.kit.render.*;
import java.lang.reflect.*;
import java.util.*;
import com.sanangeles.academycity.kit.entity.*;
import com.sanangeles.academycity.kit.item.*;

public final class Launcher
{
	public static Header header;
	
	public final void initialization(final Activity Context, InvocationHandler handler)
	{
		GameData.MinecraftActivity = Context;
		GameData.mDisplayMetrics = Context.getResources().getDisplayMetrics();
		GameData.mBitmapUtils = new BitmapUtils(Context);
		
		header = (Header)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Header.class}, handler);
		
		Looper.prepare();
		GameData.mRenderTicker = new Ticker(
			new Runnable() {
				public void run() {
					try {
						if (GameData.inGame)
							renderTick();
					} catch (Exception e) {
						displayDialog(collection(e.getStackTrace()));
					}
				}
			}
		, 500);
		
		GameData.currentLanguage = Runner.evaluate(Runner.getClassName(), "getLanguage()").toString();
		GameData.currentMinecraftVersion = Runner.evaluate(Runner.getClassName(), "getMinecraftVersion()").toString();
		
		MainScreen.initialization();
		HudScreen.initialization();
		InventoryScreen.initialization();
		
		Items.initialization();
		
		//GameData.mGameWindow = new BaseFloat(Context);
	
	}
	
	public static void displayDialog(final String e) {
		GameData.MinecraftActivity.runOnUiThread(
			new Runnable() {
				public void run() {
					new AlertDialog.Builder(GameData.MinecraftActivity)
						.setTitle("Exception")
						.setMessage(e)
						.show();
				}
			}
		);
	}
	
	public static String collection(Object[] list) {
		StringBuilder str = new StringBuilder();
		for (Object e : list) {
			str.append(e);
			str.append("\n");
		}
		return str.toString();
	}
	
	public final void renderTick() {
		//ParticleProducer.spawn(0x20, GameData.player.getX(), GameData.player.getY(), GameData.player.getZ(), 1, 1, 1, 2);
		//ParticleProducer.circle(0x20, GameData.player.getPos(), new Group<Double>(1d, 1d, 1d), 10, 2f);
		
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

	public final void leaveGame() {
		GameData.inGame = false;
	}

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
		GameData.currentScreenName = screenName;
		
		if (screenName.equals(MainScreen.screenName))
			MainScreen.elseScreen();
		
		if (screenName.equals(InventoryScreen.screenName1))
			InventoryScreen.onInventoryScreen(0);
		if (screenName.equals(InventoryScreen.screenName2))
			InventoryScreen.onInventoryScreen(1);
		else
			InventoryScreen.elseScreen();
			
		if (screenName.equals(HudScreen.screenName))
			HudScreen.onHudScreen();
		else
			HudScreen.elseScreen();
	}

	public final void newLevel() {
		GameData.inGame = true;
		GameData.player = new EntityWrapper(Launcher.header.evaluate("getPlayerEnt()"));
	
		if (GameData.mRenderTicker.isStarted())
			GameData.mRenderTicker.start();

		//displayDialog(collection(new Object[] {GameData.player.getX(), GameData.player.getY(), GameData.player.getZ()}));
	}

	public final void selectLevelHook()
	{}

	public final void startDestroyBlock(int x, int y, int z, int side)
	{}

	public final void projectileHitBlockHook(long projectile, int blockX, int blockY, int blockZ, int side)
	{}

	public final void modTick()
	{}

	public final void useItem(int x, int y, int z, int itemid, int blockid, int side, int itemDamage, int blockDamage) {
		/*EntityWrapper ent;
		for(int i = 0; i < 360; ++i) {
			double sin = Math.sin(i * Math.PI / 180) * 10;
			double cos = Math.cos(i * Math.PI / 180) * 10;
			ent = new EntityWrapper(ItemEntity.spawn(new Group<Double>((double)x+sin, (double)y, (double)z+cos), 2, new ItemInstance(itemid, itemDamage)));
			ent.setImmobile(true);
		}*/
	}
	
}
