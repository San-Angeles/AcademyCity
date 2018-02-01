package com.kokic.ui.library.weiget;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;

import android.view.animation.Interpolator;

public class StereoGroup extends ViewGroup
{

    private int mStartScreen = 1;
    private float resistance = 1.8f;
    private Scroller mScroller;
    private float mAngle = 90;
    private boolean isCan3D = true;

    private Context mContext;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private Camera mCamera;
    private Matrix mMatrix;
    private int mWidth;
    private int mHeight;
    private static final int standerSpeed = 2000;
    private static final int flingSpeed = 800;
    private int addCount;
    private int alreadyAdd = 0;
    private boolean isAdding = false;
    private int mCurScreen = 1;
    private IStereoListener iStereoListener;
    private float mDownX, mDownY, mTempY;
    private boolean isSliding = false;

    private State mState = State.Normal;

    public StereoGroup(Context context) {
        this(context, null);
    }

    public StereoGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StereoGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(mContext);
    }

    /**
     * 初始化数据
     */
    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mCamera = new Camera();
        mMatrix = new Matrix();
        if (mScroller == null) {
            mScroller = new Scroller(context);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        scrollTo(0, mStartScreen * mHeight);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childTop = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                child.layout(0, childTop,
							 child.getMeasuredWidth(), childTop + child.getMeasuredHeight());
                childTop = childTop + child.getMeasuredHeight();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        switch (ev.getAction()) {
				case MotionEvent.ACTION_DOWN:
                isSliding = false;
                mDownX = x;
                mTempY = mDownY = y;
                if (!mScroller.isFinished()) {

                    mScroller.setFinalY(mScroller.getCurrY());
                    mScroller.abortAnimation();
                    scrollTo(0, getScrollY());
                    isSliding = true;
                }
                break;
				case MotionEvent.ACTION_MOVE:
                if (!isSliding) {
                    isSliding = isCanSliding(ev);
                }
                break;
				default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSliding;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        float y = event.getY();
        switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
                return true;
				case MotionEvent.ACTION_MOVE:
                if (isSliding) {
                    int realDelta = (int) (mDownY - y);
                    mDownY = y;
                    if (mScroller.isFinished()) {

                        recycleMove(realDelta);
                    }
                }
                break;
				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
                if (isSliding) {
                    isSliding = false;
                    mVelocityTracker.computeCurrentVelocity(1000);
                    float yVelocity = mVelocityTracker.getYVelocity();

                    if (yVelocity > standerSpeed || ((getScrollY() + mHeight / 2) / mHeight < mStartScreen)) {
                        mState = State.ToPre;
                    } else if (yVelocity < -standerSpeed || ((getScrollY() + mHeight / 2) / mHeight > mStartScreen)) {

                        mState = State.ToNext;
                    } else {
                        mState = State.Normal;
                    }

                    changeByState(yVelocity);
                }
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public boolean isCanSliding(MotionEvent ev) {
        float moveX;
        float moveY;
        moveX = ev.getX();
        mTempY = moveY = ev.getY();
        if (Math.abs(moveY - mDownX) > mTouchSlop && (Math.abs(moveY - mDownY) > (Math.abs(moveX - mDownX)))) {
            return true;
        }
        return false;
    }

    private void changeByState(float yVelocity) {
        alreadyAdd = 0;
        if (getScrollY() != mHeight) {
            switch (mState) {
					case Normal:
                    toNormalAction();
                    break;
					case ToPre:
                    toPreAction(yVelocity);
                    break;
					case ToNext:
                    toNextAction(yVelocity);
                    break;
            }
            invalidate();
        }
    }

    /**
     * mState = State.Normal 时进行的动作
     */
    private void toNormalAction() {
        int startY;
        int delta;
        int duration;
        mState = State.Normal;
        addCount = 0;
        startY = getScrollY();
        delta = mHeight * mStartScreen - getScrollY();
        duration = (Math.abs(delta)) * 4;
        mScroller.startScroll(0, startY, 0, delta, duration);
    }

    /**
     * mState = State.ToPre 时进行的动作
     *
     * @param yVelocity 竖直方向的速度
     */
    private void toPreAction(float yVelocity) {
        int startY;
        int delta;
        int duration;
        mState = State.ToPre;
        addPre();

        int flingSpeedCount = (yVelocity - standerSpeed) > 0 ? (int) (yVelocity - standerSpeed) : 0;
        addCount = flingSpeedCount / flingSpeed + 1;

        startY = getScrollY() + mHeight;
        setScrollY(startY);

        delta = -(startY - mStartScreen * mHeight) - (addCount - 1) * mHeight;
        duration = (Math.abs(delta)) * 3;
        mScroller.startScroll(0, startY, 0, delta, duration);
        addCount--;
    }

    /**
     * mState = State.ToNext 时进行的动作
     *
     * @param yVelocity 竖直方向的速度
     */
    private void toNextAction(float yVelocity) {
        int startY;
        int delta;
        int duration;
        mState = State.ToNext;
        addNext();
        int flingSpeedCount = (Math.abs(yVelocity) - standerSpeed) > 0 ? (int) (Math.abs(yVelocity) - standerSpeed) : 0;
        addCount = flingSpeedCount / flingSpeed + 1;
        startY = getScrollY() - mHeight;
        setScrollY(startY);
        delta = mHeight * mStartScreen - startY + (addCount - 1) * mHeight;

        duration = (Math.abs(delta)) * 3;
        mScroller.startScroll(0, startY, 0, delta, duration);
        addCount--;
    }


    @Override
    public void computeScroll() {

        if (mScroller.computeScrollOffset()) {
            if (mState == State.ToPre) {
                scrollTo(mScroller.getCurrX(), mScroller.getCurrY() + mHeight * alreadyAdd);
                if (getScrollY() < (mHeight + 2) && addCount > 0) {
                    isAdding = true;
                    addPre();
                    alreadyAdd++;
                    addCount--;
                }
            } else if (mState == State.ToNext) {
                scrollTo(mScroller.getCurrX(), mScroller.getCurrY() - mHeight * alreadyAdd);
                if (getScrollY() > (mHeight) && addCount > 0) {
                    isAdding = true;
                    addNext();
                    addCount--;
                    alreadyAdd++;
                }
            } else {

                scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            }
            postInvalidate();
        }

        if (mScroller.isFinished()) {
            alreadyAdd = 0;
            addCount = 0;
        }
    }

    /**
     * 把第一个item移动到最后一个item位置
     */
    private void addNext() {
        mCurScreen = (mCurScreen + 1) % getChildCount();
        int childCount = getChildCount();
        View view = getChildAt(0);
        removeViewAt(0);
        addView(view, childCount - 1);
        if (iStereoListener != null) {
            iStereoListener.toNext(mCurScreen);
        }
    }

    /**
     * 把最后一个item移动到第一个item位置
     */
    private void addPre() {
        mCurScreen = ((mCurScreen - 1) + getChildCount()) % getChildCount();
        int childCount = getChildCount();
        View view = getChildAt(childCount - 1);
        removeViewAt(childCount - 1);
        addView(view, 0);
        if (iStereoListener != null) {
            iStereoListener.toPre(mCurScreen);
        }
    }

    private void recycleMove(int delta) {
        delta = delta % mHeight;
        delta = (int) (delta / resistance);
        if (Math.abs(delta) > mHeight / 4) {
            return;
        }
        scrollBy(0, delta);
        if (getScrollY() < 5 && mStartScreen != 0) {
            addPre();
            scrollBy(0, mHeight);
        } else if (getScrollY() > (getChildCount() - 1) * mHeight - 5) {
            addNext();
            scrollBy(0, -mHeight);
        }

    }

    public enum State {Normal, ToPre, ToNext}

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (!isAdding && isCan3D) {
            for (int i = 0; i < getChildCount(); i++) {
                drawScreen(canvas, i, getDrawingTime());
            }
        } else {
            isAdding = false;
            super.dispatchDraw(canvas);
        }
    }

    private void drawScreen(Canvas canvas, int i, long drawingTime) {
        int curScreenY = mHeight * i;

        if (getScrollY() + mHeight < curScreenY) {
            return;
        }
        if (curScreenY < getScrollY() - mHeight) {
            return;
        }
        float centerX = mWidth / 2;
        float centerY = (getScrollY() > curScreenY) ? curScreenY + mHeight : curScreenY;
        float degree = mAngle * (getScrollY() - curScreenY) / mHeight;
        if (degree > 90 || degree < -90) {
            return;
        }
        canvas.save();

        mCamera.save();
        mCamera.rotateX(degree);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();

        mMatrix.preTranslate(-centerX, -centerY);
        mMatrix.postTranslate(centerX, centerY);
        canvas.concat(mMatrix);
        drawChild(canvas, getChildAt(i), drawingTime);
        canvas.restore();

    }


    /**
     * 设置第一页展示的页面
     *
     * @param startScreen (0,getChildCount-1)
     * @return
     */
    public StereoGroup setStartScreen(int startScreen) {
        if (startScreen <= 0 || startScreen >= (getChildCount() - 1)) {
            throw new IndexOutOfBoundsException("Input valid startScreen id, please!");

        }
        this.mStartScreen = startScreen;
        this.mCurScreen = startScreen;
        return this;
    }

    /**
     * 设置移动阻力
     *
     * @param resistance (0,...)
     * @return
     */
    public StereoGroup setResistance(float resistance) {
        this.resistance = resistance;
        return this;
    }

    /**
     * 设置滚动时interpolator插补器
     *
     * @param mInterpolator
     * @return
     */
    public StereoGroup setInterpolator(Interpolator mInterpolator) {
        mScroller = new Scroller(mContext, mInterpolator);
        return this;
    }

    /**
     * 设置滚动时两个item的夹角度数
     *
     * @param mAngle [0f,180f]
     * @return
     */
    public StereoGroup setAngle(float mAngle) {
        this.mAngle = 180f - mAngle;
        return this;
    }

    /**
     * 是否开启3D效果
     *
     * @param can3D
     * @return
     */
    public StereoGroup setCan3D(boolean can3D) {
        isCan3D = can3D;
        return this;
    }

    /**
     * 跳转到指定的item
     *
     * @param itemId [0,getChildCount-1]
     * @return
     */
    public StereoGroup setItem(int itemId) {


        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();

        }
        if (itemId < 0 || itemId > (getChildCount() - 1)) {
            throw new IndexOutOfBoundsException("请输入规定范围内item位置号");

        }
        if (itemId > mCurScreen) {

            toNextAction(-standerSpeed - flingSpeed * (itemId - mCurScreen - 1));
        } else if (itemId < mCurScreen) {

            toPreAction(standerSpeed + (mCurScreen - itemId - 1) * flingSpeed);
        }

        return this;
    }

    /**
     * 上一页
     *
     * @return
     */
    public StereoGroup toPre() {
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();

        }
        toPreAction(standerSpeed);
        return this;
    }

    /**
     * 下一页
     *
     * @return
     */
    public StereoGroup toNext() {
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();

        }
        toNextAction(-standerSpeed);
        return this;
    }


    public interface IStereoListener {

        void toPre(int curScreen);


        void toNext(int curScreen);
    }

    public void setiStereoListener(IStereoListener iStereoListener) {
        this.iStereoListener = iStereoListener;
    }
}
