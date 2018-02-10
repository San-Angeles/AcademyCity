package com.sanangeles.academycity.kit.render;

public class BlockRenderer
{
	private final long pointer;

	public BlockRenderer(long pointer) {
		this.pointer = pointer;
	}
	
	public static native long getPointer(int id);
	public static native void setVisualShape(long pointer, float startX, float startY, float startZ, float endX, float endY, float endZ);
}
