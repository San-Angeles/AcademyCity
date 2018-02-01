package com.kokic.ui.library.weiget;

import android.animation.*;
import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import com.kokic.ui.library.util.*;

public class ColorBall extends View
{
    private static final int STRETCHING_X = 150;
    private static final int STRETCHING_Y = 80;
    private static final float BALL_MAX = 1 / 10F;
    private static final float DE_VIEW_SIZE = 120F;
    private static final long ANIMATION_TIME = 1200;
    private static final long INTERVAL_TIME = 400;

	private final int DE_BALL_SIZE = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
    private Paint mPaint;
    private int mFirstBallColor;
    private int mSecondBallColor;
    private int mThirdBallColor;
    private int mBallRadius;

    private int mWidth;
    private int mHeight;

    private int cx, cy, cx1, cy1, cx2, cy2;
    private int offsetY, offsetY1, offsetY2;
    private boolean isDrawSecond, isDrawThird;

    public ColorBall(Context context) {
        this(context, null);
    }

    public ColorBall(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorBall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

		mFirstBallColor = ColorFactory.VIA_RED;
		mSecondBallColor = ColorFactory.VIA_YELLOW;
		mThirdBallColor = ColorFactory.VIA_BLUE;

		mBallRadius = DE_BALL_SIZE;

		setBackgroundColor(Color.TRANSPARENT);

        init();
        startAllBallAnimation();
    }

	public void setFirstBallColor(int FirstBallColor) {
		this.mFirstBallColor = FirstBallColor;
	}

	public int getFirstBallColor() {
		return mFirstBallColor;
	}

	public void setSecondBallColor(int SecondBallColor) {
		this.mSecondBallColor = SecondBallColor;
	}

	public int getSecondBallColor() {
		return mSecondBallColor;
	}

	public void setThirdBallColor(int ThirdBallColor) {
		this.mThirdBallColor = ThirdBallColor;
	}

	public int getThirdBallColor() {
		return mThirdBallColor;
	}

	public void setBallRadius(int BallRadius) {
		this.mBallRadius = BallRadius;
	}

	public int getBallRadius() {
		return mBallRadius;
	}

    private void startAllBallAnimation() {
		initAnimation();
		postDelayed(new Runnable() {
				public void run() {
					initAnimation1();
				}
			}, INTERVAL_TIME / 2);
		postDelayed(new Runnable() {
				public void run() {
					initAnimation2();
				}
			}, INTERVAL_TIME);
	}

    private void init() {
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize;
        int heightSize;

        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED) {
            widthSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DE_VIEW_SIZE, getResources().getDisplayMetrics());
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
        }

        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED) {
            heightSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DE_VIEW_SIZE, getResources().getDisplayMetrics());
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mBallRadius = (int) ((int) (Math.min(Math.min(w, h) * BALL_MAX, mBallRadius)) * .5F);

        cx = mBallRadius;
        cy = mHeight / 2;
        cx1 = cx;
        cx2 = cx;
        cy1 = cy;
        cy2 = cy;
    }

    @Override
    protected void onDraw(Canvas canvas) {
		//super.onDraw(canvas);
        drawBall(canvas);
    }

    private void drawBall(Canvas canvas) {
        mPaint.setColor(mFirstBallColor);
        canvas.drawCircle(cx, cy + offsetY, mBallRadius, mPaint);
        if (isDrawSecond) {
            mPaint.setColor(mSecondBallColor);
            canvas.drawCircle(cx1, cy1 + offsetY1, mBallRadius, mPaint);
        }
        if (isDrawThird) {
            mPaint.setColor(mThirdBallColor);
            canvas.drawCircle(cx2, cy2 + offsetY2, mBallRadius, mPaint);
        }

    }

    private void initAnimation() {
        ValueAnimator valueAnimator = getValueAni();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					Float aFloat = Float.valueOf(animation.getAnimatedValue().toString());
					cx = (int) (aFloat * STRETCHING_X) + mBallRadius;
					offsetY = (int) ((float) Math.sin(2 * Math.PI * aFloat) * STRETCHING_Y);
					invalidate();
				}
			});
        valueAnimator.start();
    }


    private void initAnimation1() {
        ValueAnimator valueAnimator = getValueAni();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					Float aFloat = Float.valueOf(animation.getAnimatedValue().toString());
					cx1 = (int) (aFloat * STRETCHING_X) + mBallRadius;
					offsetY1 = (int) ((float) Math.sin(2 * Math.PI * aFloat) * STRETCHING_Y);
					isDrawSecond = true;
					invalidate();
				}
			});
        valueAnimator.start();
    }


    private void initAnimation2() {
        ValueAnimator valueAnimator = getValueAni();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					Float aFloat = Float.valueOf(animation.getAnimatedValue().toString());
					cx2 = (int) (aFloat * STRETCHING_X) + mBallRadius;
					offsetY2 = (int) ((float) Math.sin(2 * Math.PI * aFloat) * STRETCHING_Y);
					isDrawThird = true;
					invalidate();
				}
			});
        valueAnimator.start();
    }

    private ValueAnimator getValueAni() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1.1f);
        valueAnimator.setDuration(ANIMATION_TIME);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);

        return valueAnimator;
    }
}

