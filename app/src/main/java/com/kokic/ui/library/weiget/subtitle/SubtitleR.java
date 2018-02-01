package com.kokic.ui.library.weiget.subtitle;

import android.content.*;
import android.widget.*;

public final class SubtitleR extends TextView
{
	private Context mContext;
	
	private String title;
	private String messages;

	public SubtitleR(Context mContext, String title, String messages) {
		super(mContext);
		this.mContext = mContext;
		setText(requestTitle(this.title = title) + requestMessages(this.messages = messages));
	}
	
	public SubtitleR(Context mContext) {
		super(mContext);
		this.mContext = mContext;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getMessages() {
		return messages;
	}
	
	private String requestTitle(String text) {
		return text;
	}
	
	private String requestMessages(String text) {
		return text;
	}
}

final abstract interface SubtitleObserver
{
	public String getTitle()
	public String getMessages()
	
	public void setTitle(String p);
	public void setMessages(String p);
	
	public void display();
	public void dismiss();
}
