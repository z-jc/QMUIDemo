package com.qmui.android.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.util.ToastUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiGuangShareActivity extends BaseActivity {

    private String TAG = getClass().getSimpleName();
    @BindView(R.id.topbar)
    public QMUITopBar topbar;
    @BindView(R.id.btn_share_text)
    public Button btnShareText;
    @BindView(R.id.btn_share_url)
    public Button btnShareUrl;
    @BindView(R.id.btn_share_image)
    public Button btnShareImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiguang_share);
        ButterKnife.bind(this);
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("极光分享");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });
    }

    @Override
    public void initView() {}

    @OnClick({R.id.btn_share_text, R.id.btn_share_url, R.id.btn_share_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_share_text:

                break;
            case R.id.btn_share_url:

                break;
            case R.id.btn_share_image:

                break;
        }
    }
}