package com.qmui.android.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class OkgoActivity extends BaseActivity {
    private String TAG = getClass().getSimpleName();

    @BindView(R.id.btn)
    public Button btn;
    @BindView(R.id.topbar)
    public QMUITopBar topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okgo);
        ButterKnife.bind(this);
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("约束布局和Okgo");
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

    private void okgo() {
        String url = "https://suggest.taobao.com/sug?code=utf-8&q=男士鞋&callback=cb";
        OkGo.<String>get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.e(TAG, "error:" + response.getException());
                        ToastUtil.showShortToastCenter(OkgoActivity.this, "" + response.getException());
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.e(TAG, "body:" + body);
                        ToastUtil.showShortToastCenter(OkgoActivity.this, "" + body);
                    }
                });

    }

    @OnClick({R.id.btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                ToastUtil.showShortToastCenter(this,"正在获取...");
                okgo();
                break;
        }
    }
}