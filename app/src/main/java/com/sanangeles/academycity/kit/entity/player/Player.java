package com.sanangeles.academycity.kit.entity.player;

import com.sanangeles.academycity.*;

public final class Player
{
	public static long player;
	
	public static void clearCarriedItem() {
		Launcher.header.evaluate("Entity.setCarriedItem(getPlayerEnt(), 0, 0, 0);");
	}
}
