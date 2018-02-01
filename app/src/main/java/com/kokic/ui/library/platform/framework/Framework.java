package com.kokic.ui.library.platform.framework;

import android.content.*;
import com.kokic.ui.library.platform.framework.control.*;
import android.os.*;
import android.net.*;
import java.io.*;
import android.graphics.drawable.*;
import android.content.res.*;
import android.content.res.Resources.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.*;
import android.database.*;
import android.graphics.*;
import android.view.*;
import android.content.pm.*;

public class Framework extends android.view.ContextThemeWrapper
{
	private BaseContainer container = null;
	
	private int baseWidth;
	private int baseHeight;

	public Framework(int baseWidth, int baseHeight) {
		this.baseWidth = baseWidth;
		this.baseHeight = baseHeight;
	}

	public void setContainer(BaseContainer container) {
		this.container = container;
	}

	public BaseContainer getContainer() {
		return container;
	}

	public void setBaseWidth(int baseWidth) {
		this.baseWidth = baseWidth;
	}

	public int getBaseWidth() {
		return baseWidth;
	}

	public void setBaseHeight(int baseHeight) {
		this.baseHeight = baseHeight;
	}

	public int getBaseHeight() {
		return baseHeight;
	}
	
	public Framework() {}
}
