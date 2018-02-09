package com.sanangeles.academycity.kit.entity;

import com.sanangeles.academycity.kit.*;
import com.sanangeles.academycity.kit.item.*;
import com.sanangeles.academycity.*;

public class ItemEntity
{
	private final long pointer;

	public ItemEntity(long pointer) {
		this.pointer = pointer;
	}
	
	public final static long spawn(Group<Double> pos, double offset, ItemInstance ItemInstance) {
		return Launcher.header.evaluate(Runner.wrapper(GameData.getClassName(), "dropItem", pos.get(0), pos.get(1), pos.get(2), offset, ItemInstance.getId(), ItemInstance.getCount(), ItemInstance.getData()));
	}
}
