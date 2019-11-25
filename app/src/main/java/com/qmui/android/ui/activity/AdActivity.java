package com.qmui.android.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.util.AdManager;
import com.qmui.android.util.ToastUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qq.e.o.ads.v2.ads.video.RewardVideoAD;
import com.qq.e.o.ads.v2.ads.video.RewardVideoADListener;
import com.qq.e.o.ads.v2.error.AdError;
import com.sigmob.sdk.base.models.sigdsp.pb.Ad;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdActivity extends BaseActivity implements RewardVideoADListener {

    @BindView(R.id.btn_preload)
    public Button btnPreload;
    @BindView(R.id.btn_show)
    public Button btnShow;
    @BindView(R.id.btn_load)
    public Button btnLoad;
    private String TAG = getClass().getSimpleName();

    @BindView(R.id.topbar)
    public QMUITopBar topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        ButterKnife.bind(this);
        initView();
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

    private RewardVideoAD videoAD = null;

    @OnClick({R.id.btn_preload, R.id.btn_show, R.id.btn_load})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_preload:
                ToastUtil.showShortToastCenter(this, "预加载");
                videoAD = AdManager.getInstance().getRewardVideoAd(this, this);
                videoAD.preloadAD();
                break;
            case R.id.btn_show:
                ToastUtil.showShortToastCenter(this, "展示广告");
                if (videoAD != null) {
                    videoAD.showAD();
                } else {
                    ToastUtil.showShortToastCenter(this, "先预加载再显示");
                }
                break;
            case R.id.btn_load:
                AdManager.getInstance().getRewardVideoAd(this, this).loadAD();
                break;
        }
    }

    @Override
    public void onADLoad() {

    }

    @Override
    public void onVideoCached() {

    }

    @Override
    public void onADShow() {

    }

    @Override
    public void onADExpose() {

    }

    @Override
    public void onReward() {

    }

    @Override
    public void onADClick() {

    }

    @Override
    public void onVideoComplete() {

    }

    @Override
    public void onADClose() {
        ToastUtil.showShortToastCenter(this, "广告关闭");
        btnShow.setTextColor(0xff999999);
        btnShow.setEnabled(false);
        btnPreload.setTextColor(0xff000000);
        btnPreload.setEnabled(true);
    }

    @Override
    public void onSuccess(int i) {
        ToastUtil.showShortToastCenter(this, "播放完成");
        btnShow.setTextColor(0xff999999);
        btnShow.setEnabled(false);
        btnPreload.setTextColor(0xff000000);
        btnPreload.setEnabled(true);
    }

    @Override
    public void onFailed(int i, AdError adError) {
        ToastUtil.showShortToastCenter(this, "播放失败");
        btnShow.setTextColor(0xff999999);
        btnShow.setEnabled(false);
        btnPreload.setTextColor(0xff000000);
        btnPreload.setEnabled(true);
    }

    @Override
    public void onSkippedVideo() {

    }

    @Override
    public void onPreload() {
        ToastUtil.showShortToastCenter(this, "预加载完成");
        btnShow.setEnabled(true);
        btnPreload.setEnabled(false);
        btnPreload.setTextColor(0xff999999);
        btnShow.setTextColor(0xff000000);
    }
}