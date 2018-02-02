package com.sanangeles.academycity.kit.entity.player;

import com.sanangeles.academycity.*;
import com.sanangeles.academycity.kit.*;
import com.sanangeles.academycity.kit.item.*;

public final class EntityWrapper
{
	public final long data;
	
	public final EntityWrapper(long pointer) {
		this.data = pointer;
	}
	
	public final void clearCarriedItem() {
		Launcher.header.evaluate(getClassName() + "setCarriedItem(" + data + ", 0, 0, 0)");
	}
	
	public final int getArmor(int slot) {
		return Launcher.header.evaluate(getClassName() + "getArmor(" + data + "," + slot + ")");
	}
	
	public final ItemInstance getCarriedItem() {
		if (equals(GameData.player))
			return new ItemInstance(
				Launcher.header.evaluate("Player.getCarriedItem()"),
				Launcher.header.evaluate("Player.getCarriedItemCount()"),
				Launcher.header.evaluate("Player.getCarriedItemData()"));
		else
			return GameData.mEmptyItemInstance;
			
	}
	
	public final void setCarriedItem(ItemInstance itemInstance) {
		Launcher.header.evaluate(getClassName() + "setCarriedItem(" + data + "," + itemInstance.getId() + "," + itemInstance.getCount() + "," + itemInstance.getData() + ")");
	}
	
	public final void remove() {
		
	}

	public String getClassName() {
		return "Entity.";
	}
}
