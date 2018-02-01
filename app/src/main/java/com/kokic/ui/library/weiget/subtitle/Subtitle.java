package com.kokic.ui.library.weiget.subtitle;

import android.widget.*;
import com.kokic.ui.library.util.*;
import com.kokic.ui.library.view.implement.*;

public class Subtitle implements SubtitleObserver
{
	private static PopupWindow mPopupWindow;
	private static SubtitleR self;
	
	private static boolean displayed;
	
	static {
		mPopupWindow = new PopupWindow(Adapter.currentWidth, Adapter.currentHeight);
		self = new SubtitleR(UIUtil.mContext);
		displayed = false;
	}
	
	public Subtitle(String title, String messages) {
		self.setTitle(title);
		self.setMessages(messages);
	}

	@Override
	public void display() {
		if (!displayed) {
			displayed = true;
			mPopupWindow.showAtLocation(UIUtil.mDecView, UIListener.center|UIListener.bottom, 0, 0);
		}
	}

	@Override
	public void dismiss() {
		if (displayed) {
			displayed = false;
			mPopupWindow.dismiss();
		}
	}

	@Override
	public String getTitle() {
		return self.getTitle();
	}

	@Override
	public String getMessages() {
		return self.getMessages();
	}

	@Override
	public void setTitle(String p) {
		
	}

	@Override
	public void setMessages(String p) {
		
	}

	public static final class Adapter
	{
		public static int currentWidth = UIUtil.screenWidth;
		public static int currentHeight = UIUtil.screenHeight/2;
		
	}
}


