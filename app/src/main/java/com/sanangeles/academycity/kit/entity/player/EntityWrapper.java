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
		Runner.evaluate(getClassName(), "setCarriedItem(", data, ", 0, 0, 0)");
	}
	
	public final int getArmor(int slot) {
		return Runner.evaluate(getClassName(), "getArmor(", data, ",", slot, ")");
	}
	
	public final int getEntityTypeId() {
		return Runner.evaluate(getClassName(), "getEntityTypeId(", data, ")");
	}
	
	public final String getExtraData() {
		return (String)Runner.evaluate(getClassName(), "getExtraData(", data, ")");
	}
	
	public final int getHealth() {
		return Runner.evaluate(getClassName(), "getHealth(", data, ")");
	}
	
	public final int getItemEntityCount() {
		return Runner.evaluate(getClassName(), "getItemEntityCount(", data, ")");
	}
	
	public final int getItemEntityData() {
		return Runner.evaluate(getClassName(), "getItemEntityData(", data, ")");
	}
	
	public final int getItemEntityId() {
		return Runner.evaluate(getClassName(), "getItemEntityId(", data, ")");
	}
	
	public final int getMaxHealth() {
		return Runner.evaluate(getClassName(), "getMaxHealth(", data, ")");
	}

	public final String getMobSkin() {
		return (String)Runner.evaluate(getClassName(), "getItemEntityData(", data, ")");
	}

	public final String getNameTag() {
		return (String)Runner.evaluate(getClassName(), "getItemEntityId(", data, ")");
	}
	
	public final double getPitch() {
		return Runner.evaluate(getClassName(), "getPitch(", data, ")");
	}

	public final int getRenderType() {
		return Runner.evaluate(getClassName(), "getRenderType(", data, ")");
	}

	public final EntityWrapper getRider() {
		return new EntityWrapper(Runner.evaluate(getClassName(), "getItemEntityData(", data, ")"));
	}

	public final EntityWrapper getTarget() {
		return new EntityWrapper(Runner.evaluate(getClassName(), "getItemEntityId(", data, ")"));
	}
	
	public final Object getUniqueId() {
		return Runner.evaluate(getClassName(), "getMaxHealth(", data, ")");
	}

	public final double getVelX() {
		return Runner.evaluate(getClassName(), "getVelX(", data, ")");
	}

	public final double getVelY() {
		return Runner.evaluate(getClassName(), "getVelY(", data, ")");
	}

	public final double getVelZ() {
		return Runner.evaluate(getClassName(), "getVelZ(", data, ")");
	}

	public final int getX() {
		return Runner.evaluate(getClassName(), "getX(", data, ")");
	}

	public final int getY() {
		return Runner.evaluate(getClassName(), "getItemEntityData(", data, ")");
	}

	public final int getZ() {
		return Runner.evaluate(getClassName(), "getZ(", data, ")");
	}
	
	public final ItemInstance getCarriedItem() {
		if (equals(GameData.player))
			return new ItemInstance(
				Runner.evaluate(Player.getClassName(), "getCarriedItem()"),
				Runner.evaluate(Player.getClassName(), "getCarriedItemCount()"),
				Runner.evaluate(Player.getClassName(), "getCarriedItemData()")
			);
		else
			return GameData.mEmptyItemInstance;	
	}
	
	public final void setCarriedItem(ItemInstance itemInstance) {
		Runner.evaluate(getClassName(), "setCarriedItem(", data, ",", itemInstance.getId(), ",", itemInstance.getCount(), ",", itemInstance.getData(), ")");
	}
	
	public final void remove() {
		Runner.evaluate(getClassName(), "remove(", data, ")");
	}

	public final static String getClassName() {
		return "Entity.";
	}
}
