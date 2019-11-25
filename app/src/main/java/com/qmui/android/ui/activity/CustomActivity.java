package com.qmui.android.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.bean.TouchEvent;
import com.qmui.android.ui.view.CustomView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * CreateName : Z-JC
 * Date : 2019/10/23
 * Describe :
 */
public class CustomActivity extends BaseActivity {

    @BindView(R.id.topbar)
    public QMUITopBar topbar;
    @BindView(R.id.custom)
    public CustomView customview;
    @BindView(R.id.text)
    public TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("自定义View");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });

        customview.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                Log.e(TAG, "点击事件:" + v.getId());
            }
        });

        customview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //EventBus.getDefault().post(new TouchEvent("----->onTouch"));
                return false;
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTouchMsg(TouchEvent event) {
        text.setText(event.touchMsg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}