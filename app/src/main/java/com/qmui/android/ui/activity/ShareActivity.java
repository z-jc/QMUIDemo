package com.qmui.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.util.ShareUtils;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author : Z-JC
 * Date : 2019/11/25
 * Description :
 */
public class ShareActivity extends BaseActivity {

    @BindView(R.id.img_photo)
    public ImageView imgPhoto;
    @BindView(R.id.tv_image_data)
    public TextView tvImageData;
    @BindView(R.id.btn_back)
    public QMUIRoundButton btnBack;
    @BindView(R.id.btn_share)
    public QMUIRoundButton btnShare;

    public String path;
    public String size;
    public String cc;

    public static Intent newIntent(Activity activity, String filePath, String fileSize, String fileCc) {
        Intent intent = new Intent(activity, ShareActivity.class);
        intent.putExtra("filePath", filePath);
        intent.putExtra("fileSize", fileSize);
        intent.putExtra("fileCc", fileCc);
        return intent;
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_share;
    }

    @Override
    public void initView() {
        path = getIntent().getStringExtra("filePath");
        size = getIntent().getStringExtra("fileSize");
        cc = getIntent().getStringExtra("fileCc");

        Glide.with(this).load(path).into(imgPhoto);
        tvImageData.setText(path + "\n" + size + "\n" + cc);
    }

    @OnClick({R.id.btn_back, R.id.btn_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                this.finish();
                break;
            case R.id.btn_share:
                ShareUtils.sendShare(new File(path), this);
                break;
        }
    }
}
