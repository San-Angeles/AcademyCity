package com.sanangeles.academycity.kit.item;

import com.sanangeles.academycity.*;
import com.sanangeles.academycity.kit.item.*;
import com.sanangeles.academycity.kit.entity.player.*;

public final class ItemInstance
{
	private final int id;
	private final int count;
	private final int data;

	public ItemInstance(int id) {
		this(id, 1, 0);
	}
	
	public ItemInstance(int id, int data) {
		this(id, 1, data);
	}
	
	public ItemInstance(int id, int count, int data) {
		this.id = id;
		this.count = count;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public int getCount() {
		return count;
	}

	public int getData() {
		return data;
	}
	
	public final static void addItemCreative(int id, int count, int data) {
		Runner.evaluate(Player.getClassName(), "addItemCreativeInv(", id, ",", count, ",", data, ")");
	}
	
	public final static void addItemSurvival(int id, int count, int data) {
		Runner.evaluate(Player.getClassName(), "addItemInventory(", id, ",", count, ",", data, ")");
	}
}
