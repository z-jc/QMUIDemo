package com.qmui.android.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Author : Z-JC
 * Date : 2019/11/13
 * Description :
 */
public class CustomView extends View {

    private String TAG = getClass().getSimpleName();

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "手指刚接触屏幕");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "手指移动");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "手指离开屏幕");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "手指刚接触屏幕");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "手指移动");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "手指离开屏幕");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        Log.e(TAG, "---->  setOnClickListener");
        super.setOnClickListener(l);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        Log.e(TAG, "---->  setOnTouchListener");
        super.setOnTouchListener(l);
    }
}