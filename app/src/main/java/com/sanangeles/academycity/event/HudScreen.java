package com.sanangeles.academycity.event;

import android.view.*;
import android.view.View.*;
import com.sanangeles.academycity.*;
import com.sanangeles.academycity.gui.*;
import com.sanangeles.academycity.kit.entity.player.*;
import com.sanangeles.academycity.kit.gui.*;

public class HudScreen
{
	public static final String screenName = "hud_screen";
	
	public static Popuper basePopuper;
	public static IButton clearItemButton;
	
	public final static void initialization() {
		
		clearItemButton = new IButton(UIAdapter.dip2px(48), UIAdapter.dip2px(48)).render();
		basePopuper = new Popuper(clearItemButton, UIAdapter.dip2px(48), UIAdapter.dip2px(48));
		

		clearItemButton.setText("poib");
		clearItemButton.setTextSize(12);
		clearItemButton.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View view) {

				}
			}
		);
	
	}
	
	public final static void onHudScreen() {
		
		if (!basePopuper.getPopupWindow().isShowing())
			basePopuper.show(0, UIAdapter.dip2px(48));
		
	}
	
	public final static void elseScreen()
	{}
}
