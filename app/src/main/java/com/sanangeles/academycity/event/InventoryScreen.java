package com.sanangeles.academycity.event;

import android.view.*;
import android.view.View.*;
import com.sanangeles.academycity.*;
import com.sanangeles.academycity.gui.*;
import com.sanangeles.academycity.kit.entity.player.*;
import com.sanangeles.academycity.kit.gui.*;

public final class InventoryScreen
{
	public static final String screenName1 = "survival_inventory_screen";
	public static final String screenName2 = "creative_inventory_screen";
	
	public static Popuper basePopuper;
	public static IButton clearItemButton;

	public final static void initialization() {
		clearItemButton = new IButton(UIAdapter.dip2px(48), UIAdapter.dip2px(48)).render();
		basePopuper = new Popuper(clearItemButton, UIAdapter.dip2px(48), UIAdapter.dip2px(48));
		
		clearItemButton.setText("Tab");
		clearItemButton.setTextSize(12);
		clearItemButton.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View view) {
					
				}
			}
		);
	}
	
	public final static void onInventoryScreen(int type) {
		if (!basePopuper.getPopupWindow().isShowing())
			basePopuper.show(0, UIAdapter.dip2px(48));
	}
	
	public final static void elseScreen() {
		if (basePopuper.getPopupWindow().isShowing()) {
			GameData.MinecraftActivity.runOnUiThread(
				new Runnable() {
					@Override
					public void run() {
						basePopuper.getPopupWindow().dismiss();
					}
				}
			);
		}
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
