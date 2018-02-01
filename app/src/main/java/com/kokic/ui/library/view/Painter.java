package com.kokic.ui.library.view;

import android.content.*;
import android.graphics.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.kokic.ui.library.view.implement.*;

@android.widget.RemoteViews.RemoteView()
public class Painter extends Button implements UIListener
{
	private Paint mPaint;
	private Canvas canvas;

	private int baseWidth;
	private int baseHeight;
	private int currentWidth;
	private int currentHeight;

	private PainterListener listener = new PainterListener()
	{
		@Override
		public void onPreDraw() {}

		@Override
		public void onTouch(int event) {}

		@Override
		public void onDraw(int width, int height) {}

		@Override
		public void onSizeChanged(int currentWidth, int currentHeight, int baseWidth, int baseHeight) {}
	};

	public boolean onTouch = false;

	private OnTouchListener onTouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View self, MotionEvent event) {
			int action = event.getAction();
			listener.onTouch(action);

			if (action == MotionEvent.ACTION_UP)
				onTouch = false;
			else if (action == MotionEvent.ACTION_DOWN)
				onTouch = true;

			invalidate();
			return false;
		}
	};
	
	public Painter(Context context) {
		super(context);
		setBackground(null);
		mObserver = getViewTreeObserver();
		mObserver.addOnPreDrawListener(
		    new ViewTreeObserver.OnPreDrawListener() {
				@Override
				public boolean onPreDraw() {
					baseWidth = currentWidth = width = getWidth();
					baseHeight = currentHeight = height = getHeight();
					mParams = getLayoutParams();
					listener.onPreDraw();
					return true;
				}
			}
		);

		mObserver.addOnDrawListener(
		    new ViewTreeObserver.OnDrawListener() {
				public void onDraw() {}
			}
		);

		setOnTouchListener(onTouchListener);
		mPaint = new Paint();
		
	}
	
	public Painter(Context context, AttributeSet attr) {
		super(context, attr);
		setBackground(null);
		mObserver = getViewTreeObserver();
		mObserver.addOnPreDrawListener(
		    new ViewTreeObserver.OnPreDrawListener() {
				@Override
				public boolean onPreDraw() {
					baseWidth = currentWidth = width = getWidth();
					baseHeight = currentHeight = height = getHeight();
					mParams = getLayoutParams();
					listener.onPreDraw();
					return true;
				}
			}
		);

		mObserver.addOnDrawListener(
		    new ViewTreeObserver.OnDrawListener() {
				public void onDraw() {}
			}
		);

		setOnTouchListener(onTouchListener);
		mPaint = new Paint();
		
	}
	
	public Paint getUIPaint() {
		return mPaint;
	}

	public int getBaseWidth() {
		return baseWidth;
	}

	public int getBaseHeight() {
		return baseHeight;
	}

	public int getCurrentWidth() {
		return currentWidth;
	}

	public int getCurrentHeight() {
		return currentHeight;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setListener(PainterListener listener) {
		this.listener = listener;
	}

	public PainterListener getListener() {
		return listener;
	}

	public static class Renderer
	{
		public static Runnable template = new Runnable() {
			@Override
			public void run() {}
		};

		public final static Renderer[] BasePanel = {
			new Painter.Renderer(new int[] {0xFFBCB1AB, 0xFF958681, 0xFF28272A, 0xFF716567}, Painter.Renderer.Type.rectangle_panel),
			new Painter.Renderer(new int[] {0xFF28272A, 0xFF716567, 0xFFBAAFA9, 0xFF716567}, Painter.Renderer.Type.rectangle_panel)
		};

		public final static Renderer[] BorderPanel = {
			new Painter.Renderer(new int[] {0xFFFFFFFF, 0xFFC6C6C6, 0xFF585658, 0xFFC6C6C6, 0xFF131313}, Painter.Renderer.Type.rectangle_frame),
			new Painter.Renderer(new int[] {0xFF585658, 0xFFC6C6C6, 0xFFFFFFFF, 0xFFC6C6C6, 0xFF131313}, Painter.Renderer.Type.rectangle_frame),
			new Painter.Renderer(new int[] {0xFFEFF1FF, 0xFFCAD1FF, 0xFF6C77B7, 0xFFCAD1FF, 0xFF131313}, Painter.Renderer.Type.rectangle_frame),
			new Painter.Renderer(new int[] {0xFF6C77B7, 0xFFCAD1FF, 0xFFEFF1FF, 0xFFCAD1FF, 0xFF131313}, Painter.Renderer.Type.rectangle_frame)
		};

		public final static Renderer[] Material = {
			new Painter.Renderer(new int[] {0xFF0086F1, 0xFF3E58C9, 0xFF3448A1, 0xFF3E58C9}, Painter.Renderer.Type.rectangle_panel),
			new Painter.Renderer(new int[] {0xFF3448A1, 0xFF3E58C9, 0xFF0086F1, 0xFF3E58C9}, Painter.Renderer.Type.rectangle_panel)
		};

		public final static Renderer[] NetPanel = {
			new Renderer(new int[] {0xFF31B006, 0xFF64FC1E, 0xFF8AFD5A,0xFF146D00, 0xFF30AE1F, 0xFF45CF08,0xFF125802, 0xFF1D8C01, 0xFF22B203}, Painter.Renderer.Type.rectangle_nine),
			new Renderer(new int[] {0xFF3D3D3D, 0xFF272727, 0xFF171717, 0xFF272727, 0xFF000000}, Painter.Renderer.Type.rectangle_panel)
		};

		public final static Renderer[] NetPanelFrame = {
			new Renderer(new int[] {0xFF31B006, 0xFF64FC1E, 0xFF8AFD5A,0xFF146D00, 0xFF30AE1F, 0xFF45CF08,0xFF125802, 0xFF1D8C01, 0xFF22B203}, Painter.Renderer.Type.rectangle_nine_frame),
			new Renderer(new int[] {0xFF3D3D3D, 0xFF272727, 0xFF171717, 0xFF272727, 0xFF000000}, Painter.Renderer.Type.rectangle_frame)
		};
		
		private final int[] colors;
		private final Type type;

		public Renderer(int[] colors, Type type) {
			this.colors = colors;
			this.type = type;
		}

		public int[] getColors() {
			return colors;
		}

		public Type getType() {
			return type;
		}

		public static void setPx(int px) {
			Renderer.px = px;
		}

		public static int getPx() {
			return px;
		}

		public static int convers(int d) {
			return (d - 2) / (d / 8);
		}

		public static enum Type
		{
			rectangle_panel,
			rectangle_button,
			rectangle_button_full,

			rectangle_frame,

			rectangle_nine,
			rectangle_nine_frame,

			tab_element,
			tab_element_up,
			tab_element_down,
			tab_element_left,
			tab_element_right,

			custom
		}

		public static int px = 2;

		public static void render(Painter painter, Renderer renderer) {
			render(painter, renderer.colors, renderer.type);
		}

		public static void render(Painter self, int[] colors, Type type) {

			if (type == type.custom) {
				template.run();

				return;
			}

			if (type == type.rectangle_frame) {
				self.setPaintColor(colors[4]);
				UIRenderer.frame(self, 0, 0, width, height, px);
				UIRenderer.panel(self, px, px, width - 2 * px, height - 2 * px, px, colors);
			}

			if (type == type.rectangle_nine) {
				//self.setPaintColor("#131313");
				//UIRenderer.frame(self, 0, 0, width, height, px);
				UIRenderer.ninePlate(self, 0, 0, width, height, px, colors);

				return;
			}

			if (type == type.rectangle_nine_frame) {
				self.setPaintColor(Color.BLACK);
				UIRenderer.frame(self, 0, 0, width, height, px);
				UIRenderer.ninePlate(self, px, px, width - 2 * px, height - 2 * px, px, colors);

				return;
			}

			if (type == type.rectangle_panel) {
				UIRenderer.panel(self, 0, 0, width, height, px, colors);
				return;
			}

			if (type == type.rectangle_button) {
				return;
			}

			if (type == type.rectangle_button_full) {

				self.setPaintColor(colors[0]);
				self.draw(0, 0, px, height);
				self.draw(px, 0, px * 2, height);
				self.draw(2 * px, 0, width - px, px);
				self.draw(2 * px, px, (width - px * 2), px * 2);

				self.setPaintColor(colors[1]);
				self.draw(2 * px, 2 * px, width - (px * 2), height - (px * 2));

				self.setPaintColor(colors[2]);
				self.draw(width - px, px, width, height - (px * 2));
				self.draw(width - (2 * px), px * 2, width - px, height - (px * 2));
				self.draw(2 * px, height - (2 * px), width, height - px);
				self.draw(px, height - px, width, height);

				self.setPaintColor(colors[3]);
				self.draw(0, height - px, px, height);
				self.draw(px, height - (2 * px), px * 2, height - px);
				self.draw(width - px, 0, width, px);
				self.draw(width - (2 * px), px, width - px, px * 2);

				return;
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO: Implement this method
		this.canvas = canvas;
		listener.onDraw(width, height);
		//super.onDraw(this.canvas = canvas);
	}

	public static abstract interface PainterListener
	{
		public void onPreDraw();
		public void onDraw(int width, int height)
		public void onTouch(int event)
		public void onSizeChanged(int current_width, int current_height, int base_width, int base_height)
	}

	public void setPaintColor(int color) {
		mPaint.setColor(color);
	}

	public void draw(float left, float top, float right, float bottom) {
		canvas.drawRect(left, top, right, bottom, mPaint);
	}

	@Override
	protected void onSizeChanged(int _width, int _height, int _oldwidth, int _oldheight) {
		// TODO: Implement this method
		super.onSizeChanged(currentWidth = width = _width, currentHeight = height = _height, baseWidth = _oldwidth, baseHeight = _oldheight);
		listener.onSizeChanged(_width, _height, _oldwidth, _oldheight);
	}

	public void updateOffset() {
		setTranslationX(getTranslationX() - (currentWidth - baseWidth) / 2);
		setTranslationY(getTranslationY() - (currentHeight - baseHeight) / 2);
	}

	public static Painter clone(Painter base, PainterListener listener) {
		Painter target = new Painter(base.getContext());
		target.setListener(listener == null ? base.listener : listener);
		return target;
	}
}

