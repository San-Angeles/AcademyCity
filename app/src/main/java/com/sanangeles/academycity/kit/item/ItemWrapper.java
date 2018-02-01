package com.sanangeles.academycity.kit.item;

import com.sanangeles.academycity.*;

public class ItemWrapper
{
	protected int id;
	
	public ItemWrapper(BaseItem item) {
		this.id = item.getId();
	}
	
	public ItemWrapper setHandEquipped(boolean able) {
		Launcher.header.evaluate("Item.setHandEquipped(" + this.id + "," + able + ")");
		return this;
	}
	
	public ItemWrapper setProperties(String object) {
		Launcher.header.evaluate("Item.setProperties(" + this.id + "," + object + ")");
		return this;
	}
	
	public ItemWrapper turnFood(int repair) {
		setProperties("{\"use_animation\":\"eat\",\"use_duration\":32,\"food\":{\"nutrition\":" + repair + ",\"saturation_modifier\":\"normal\"," + "\"is_meat\":false}}");
		return this;
	}
}
