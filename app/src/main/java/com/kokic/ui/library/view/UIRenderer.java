package com.kokic.ui.library.view;

import android.graphics.*;

public class UIRenderer
{
	public static void triangle(Painter painter, int[] pos) {
		Path path = new Path();
		path.moveTo(pos[0], pos[1]);
		path.lineTo(pos[2], pos[3]);
		path.lineTo(pos[4], pos[5]);
		path.close();
		painter.getCanvas().drawPath(path, painter.getUIPaint());
	}

	public static void rectangle(Painter painter, float x, float y, float width, float height) {
		painter.draw(x, y, x + width, y + height);
	}

	public static void frame(Painter painter, float x, float y, float width, float height, float px) {
		rectangle(painter, x, y, width, px);
		rectangle(painter, x, px, px, height - 2 * px);
		rectangle(painter, width - px, px, px, height - 2 * px);
		rectangle(painter, x, height - px, width, px);
	}

	public static void panel(Painter painter, float x, float y, float width, float height, float px, int[] colors) {
		painter.setPaintColor(colors[0]);
		rectangle(painter, x, y, width - px, px);
		rectangle(painter, x, y + px, px, height - 2 * px);

		painter.setPaintColor(colors[1]);
		rectangle(painter, x + px, y + px, width - 2 * px, height - 2 * px);

		painter.setPaintColor(colors[2]);
		rectangle(painter, x + width - px, y + px, px, height - px);
		rectangle(painter, x + px, height - px + y, width - 2 * px, px);

		painter.setPaintColor(colors[3]);
		rectangle(painter, x, height - px + y, px, px);
		rectangle(painter, x + width - px, y, px, px);
	}

	public static void ninePlate(Painter painter, float x, float y, float width, float height, float px, int[] colors) {
		painter.setPaintColor(colors[0]);
		rectangle(painter, x, y, px, px);

		painter.setPaintColor(colors[1]);
		rectangle(painter, x + px, y, width - 2 * px, px);

		painter.setPaintColor(colors[2]);
		rectangle(painter, width - px + x, y, px, px);

		painter.setPaintColor(colors[3]);
		rectangle(painter, x, px + y, px, height - 2 * px);

		painter.setPaintColor(colors[4]);
		rectangle(painter, px + x, px + y, width - 2 * px, height - 2 * px);

		painter.setPaintColor(colors[5]);
		rectangle(painter, width - px + x, px + y, px, height - 2 * px);

		painter.setPaintColor(colors[6]);
		rectangle(painter, x, height - px + y, px, px);

		painter.setPaintColor(colors[7]);
		rectangle(painter, px + x, height - px + y, width - 2 * px, px);

		painter.setPaintColor(colors[8]);
		rectangle(painter, width - px + x, height - px + y, px, px);

	}
}

