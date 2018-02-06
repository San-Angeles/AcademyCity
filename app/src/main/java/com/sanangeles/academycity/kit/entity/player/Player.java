package com.sanangeles.academycity.kit.entity.player;

import com.sanangeles.academycity.*;

public final class Player
{
	public final static int getGameMode() {
		return Runner.evaluate(GameData.getClassName(), "getGameMode()");
	}
	
	public final static int getLevel() {
		return Launcher.header.evaluate("getLevel()");
	}
	
	public final static int getX() {
		return Launcher.header.evaluate("getPlayerX()");
	}
	
	public final static int getY() {
		return Launcher.header.evaluate("getPlayerY()");
	}
	
	public final static int getZ() {
		return Launcher.header.evaluate("getPlayerZ()");
	}
	
	public final static String getClassName() {
		return "Player.";
	}
}
