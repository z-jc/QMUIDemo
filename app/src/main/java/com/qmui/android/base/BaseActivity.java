package com.qmui.android.base;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.qmui.android.ui.dialog.DialogLoading;
import com.qmuiteam.qmui.arch.QMUIActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;

/**
 * CreateName : Z-JC
 * Date : 2019/10/23
 * Describe :
 */
public abstract class BaseActivity extends QMUIActivity {

    public String TAG = getClass().getSimpleName();

    public String[] permission = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };

    public DialogLoading dialogLoading = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置竖屏
        QMUIStatusBarHelper.translucent(this);
        SoulPermission.getInstance().checkAndRequestPermissions(
                Permissions.build(permission),
                new CheckRequestPermissionsListener() {
                    @Override
                    public void onAllPermissionOk(Permission[] allPermissions) {
                        Log.e(BaseActivity.this.getClass().getSimpleName(), "所有权限已通过");
                    }

                    @Override
                    public void onPermissionDenied(Permission[] refusedPermissions) {
                        StringBuilder sb = new StringBuilder();
                        for (Permission permission : refusedPermissions) {
                            sb.append(permission.getPermissionNameDesc()).append("\n");
                        }
                        String desc = sb.toString();
                        new AlertDialog.Builder(BaseActivity.this)
                                .setTitle("提示")
                                .setMessage("权限异常，请前往设置－>权限管理，打开" + desc)
                                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //去设置页
                                        SoulPermission.getInstance().goApplicationSettings();
                                    }
                                }).create().show();
                    }
                });
    }

    public abstract void initView();

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