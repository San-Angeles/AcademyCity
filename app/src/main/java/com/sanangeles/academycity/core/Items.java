package com.sanangeles.academycity.core;

import com.sanangeles.academycity.kit.item.*;

public final class Items
{
	public static ItemWrapper mCoin;
	
	public static final void initialization() {
		mCoin = new ItemWrapper(new BaseItem(600, "coin", 1)); //游戏币
		ItemInstance.addItemCreative(600, 1, 0);
		
		mCoin = new ItemWrapper(new BaseItem(601, "tie", 1));  //铁矢
		ItemInstance.addItemCreative(601, 1, 0);
	}
}
