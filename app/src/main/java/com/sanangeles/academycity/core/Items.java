package com.sanangeles.academycity.core;

import com.sanangeles.academycity.kit.item.*;

public final class Items
{
	public static ItemWrapper mCoin;
	
	public static final void initialization() {
		mCoin = new ItemWrapper(new BaseItem(600, "coin", 1));
		ItemInstance.addItemCreative(600, 1, 0);
	}
}
