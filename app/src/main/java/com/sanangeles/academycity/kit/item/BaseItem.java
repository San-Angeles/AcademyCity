package com.sanangeles.academycity.kit.item;

import com.sanangeles.academycity.interfaces.*;
import com.sanangeles.academycity.*;

public class BaseItem
{
	protected final int id;
	protected String icon;
	protected int iconIndex;
	protected String name;

	public BaseItem(int id, String icon, int stack) {
		this(id, icon, 0, icon, stack);
	}
	
	public BaseItem(int id, String icon, int iconIndex, String name, int stack) {
		this.id = id;
		this.icon = icon;
		this.iconIndex = iconIndex;
		this.name = name;
		
		Launcher.header.evaluate("ModPE.setItem(" + id + ",\"" + icon + "\"," + iconIndex + ",\"" + name + "\"," + stack + ")");
	}
	
	public BaseItem setCategory(int type) {
		Launcher.header.evaluate("Item.setCategory(" + id + "," + type + ")");
		return this;
	}
	
	public BaseItem setStackedByData(boolean able) {
		Launcher.header.evaluate("Item.setStackedByData(" + id + "," + able + ")");
		return this;
	}
	
	public int getId() {
		return id;
	}
	
	public String getIcon() {
		return icon;
	}

	public int getIconIndex() {
		return iconIndex;
	}

	public void setName(String name) {
		this.name = name;
		Launcher.header.evaluate("ModPE.langEdit(\"" + getInternalName() + "\",\"" + getName() + "\")");
	}

	public String getName() {
		return name;
	}
	
	public String getInternalName() {
		return new StringBuffer("item.").append(name).append(".name").toString();
	}
}
