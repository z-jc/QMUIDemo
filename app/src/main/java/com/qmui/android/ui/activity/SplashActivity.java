package com.qmui.android.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.qmui.android.R;
import com.qmui.android.util.AdManager;
import com.qq.e.o.ads.v2.ads.splash.SplashADListener;
import com.qq.e.o.ads.v2.error.AdError;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends Activity implements SplashADListener {

    public String TAG = getClass().getSimpleName();
    @BindView(R.id.layout_ad)
    public FrameLayout layoutAd;
    private volatile boolean isStart = false;

    public String[] permission = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 23) {
            SoulPermission.getInstance().checkAndRequestPermissions(
                    Permissions.build(permission), new CheckRequestPermissionsListener() {
                        @Override
                        public void onAllPermissionOk(Permission[] allPermissions) {
                            AdManager.getInstance().showSplashAD(SplashActivity.this, layoutAd, SplashActivity.this);
                        }

                        @Override
                        public void onPermissionDenied(Permission[] refusedPermissions) {
                            AdManager.getInstance().showSplashAD(SplashActivity.this, layoutAd, SplashActivity.this);
                        }
                    });
        } else {
            // 如果是Android6.0以下的机器，默认在安装时获得了所有权限，可以直接调用SDK
            AdManager.getInstance().showSplashAD(this, layoutAd, SplashActivity.this);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isStart = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isStart) {
            startAct();
        }
        isStart = true;
    }

    private void startAct() {
        if (isStart) {
            startAct(this, new MainActivity());
            this.finish();
            isStart = false;
        } else {
            isStart = true;
        }
    }

    /**
     * Activity跳转
     *
     * @param startAct
     * @param endAct
     * @return
     */
    public void startAct(Activity startAct, Activity endAct) {
        Intent intent = new Intent(startAct, endAct.getClass());
        startActivity(intent);
    }

    @Override
    public void onNoAD(AdError adError) {
        startAct();
    }

    @Override
    public void onADPresent() {
    }

    @Override
    public void onADClicked() {
    }

    @Override
    public void onADSkip() {
        startAct();
    }

    @Override
    public void onADTimeOver() {
        startAct();
    }
}