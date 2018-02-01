package com.kokic.ui.library.util;

import android.app.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import java.io.*;

public class AssetLoader
{
	public Activity Context;
	public AssetLoader(Activity input) {
		this.Context = input;
	}

	public static class Image {
		public static Bitmap loadFile(AssetManager manager, String path, Object... args)
		throws IOException {
			return BitmapFactory.decodeStream(manager.open(path));
		}
	}

	public void outputFile(String filePath, String outputPath)
	throws IOException
	{  
        InputStream Input;  
        OutputStream Output = new FileOutputStream(outputPath);  
        Input = Context.getAssets().open(filePath);  
        byte[] buffer = new byte[1024];  
        int length = Input.read(buffer);

		while(length > 0) {
            Output.write(buffer, 0, length); 
            length = Input.read(buffer);
        }

        Output.flush();  
        Input.close();  
        Output.close();        
    }  
}

