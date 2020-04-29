package com.qmui.android.ui.activity;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.util.AdManager;
import com.qmui.android.util.ToastUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.OnClick;

public class AdActivity extends BaseActivity{

    @BindView(R.id.btn_preload)
    public Button btnPreload;
    @BindView(R.id.btn_show)
    public Button btnShow;
    @BindView(R.id.btn_load)
    public Button btnLoad;

    @BindView(R.id.topbar)
    public QMUITopBar topbar;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_ad;
    }

    @Override
    public void initView() {
        btnShow.setEnabled(false);
        btnShow.setTextColor(0xff999999);
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("激励视频广告");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });
    }

    @OnClick({R.id.btn_preload, R.id.btn_show, R.id.btn_load})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_preload:
                ToastUtil.showShortToastCenter(this, "预加载");
                AdManager.getInstance().showRewardVideoAD(this, false);
                break;
            case R.id.btn_show:
                ToastUtil.showShortToastCenter(this, "展示广告");
                AdManager.getInstance().showRewardVideoAD(this, true);
                break;
            case R.id.btn_load:
                AdManager.getInstance().showRewardVideoAD();
                break;
        }
    }
}