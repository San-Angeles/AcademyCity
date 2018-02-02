package com.sanangeles.academycity.kit.item;

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
}
