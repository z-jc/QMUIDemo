package com.qmui.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.ui.view.CustomView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("自定义View");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });

    }

}