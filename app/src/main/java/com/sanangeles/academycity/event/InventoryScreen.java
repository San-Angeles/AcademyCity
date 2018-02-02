package com.sanangeles.academycity.event;

import com.sanangeles.academycity.*;
import com.sanangeles.academycity.gui.*;
import com.sanangeles.academycity.kit.gui.*;
import android.view.View.*;
import android.view.*;
import com.sanangeles.academycity.kit.entity.player.*;

public final class InventoryScreen
{
	public static final String screenName1 = "survival_inventory_screen";
	public static final String screenName2 = "creative_inventory_screen";
	
	public static Popuper clearItemPopuper;
	public static IButton clearItemButton;

	public final static void initialization() {
		clearItemButton = new IButton((int)UIAdapter.width(96), (int)UIAdapter.height(96)).render();
		clearItemPopuper = new Popuper(clearItemButton, (int)UIAdapter.width(96), (int)UIAdapter.height(96));
		
		clearItemButton.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View view) {
					Player.clearCarriedItem();
				}
			}
		);
	}
	
	public final static void onInventoryScreen(int type) {
		if (!clearItemPopuper.getPopupWindow().isShowing())
			clearItemPopuper.show(0, (int)UIAdapter.height(128));
	}
	
	public final static void elseScreen() {
		if (clearItemPopuper.getPopupWindow().isShowing())
			GameData.MinecraftActivity.runOnUiThread(
				new Runnable() {
					@Override
					public void run() {
						clearItemPopuper.getPopupWindow().dismiss();
					}
				}
			);
	}
	
	public final static int getGameMode(String screenName) {
		return
			screenName.equals(screenName1)? 0: 1;
	}
	
	public final static boolean isInventoryScreen(String screenName) {
		return 
			screenName.equals(screenName1) ||
			screenName.equals(screenName2);
	}
}
