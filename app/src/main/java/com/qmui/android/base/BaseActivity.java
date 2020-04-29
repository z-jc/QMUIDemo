package com.qmui.android.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.qmui.android.ui.dialog.DialogLoading;
import com.qmuiteam.qmui.arch.QMUIActivity;

import butterknife.ButterKnife;

/**
 * CreateName : Z-JC
 * Date : 2019/10/23
 * Describe :
 */
public abstract class BaseActivity extends QMUIActivity {

    public String TAG = getClass().getSimpleName();
    public DialogLoading dialogLoading = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置竖屏
        setContentView(getContentViewResId());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract int getContentViewResId();
    public void initView(){}
    public void initData(){}

    public Intent getIntent(Activity startAct, Activity endAct) {
        Intent intent = new Intent(startAct, endAct.getClass());
        return intent;
    }

    public void showLoading() {
        if (dialogLoading != null) {
            dialogLoading.dismiss();
            dialogLoading = null;
        }
        dialogLoading = new DialogLoading(this);
        dialogLoading.show();
    }

    public void dismissLoading() {
        if (dialogLoading != null) {
            dialogLoading.dismiss();
            dialogLoading = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissLoading();
    }
}