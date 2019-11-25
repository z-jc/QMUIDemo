package com.qmui.android.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.ui.glide.GlideEngine;
import com.qmui.android.util.ExifInterfaceUtil;
import com.qmui.android.util.ImageUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageComPressionActivity extends BaseActivity {

    @BindView(R.id.btn_select_image)
    public QMUIRoundButton btnSelectImage;
    @BindView(R.id.topbar)
    public QMUITopBar topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_compression);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("照片采集");
        /*topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });*/
    }

    @OnClick(R.id.btn_select_image)
    public void onClick() {
        EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                .setFileProviderAuthority("com.qmui.android.fileprovider")
                .start(new SelectCallback() {
                    @Override
                    public void onResult(ArrayList<Photo> photos, final ArrayList<String> paths, boolean isOriginal) {
                        String path = ImageUtils.compressUpImage(paths.get(0));
                        Log.e(TAG, "压缩后的照片:" + path);
                        Log.e(TAG, "照片数据:" + ExifInterfaceUtil.getImagedata(path));
                        try {
                            String size = ExifInterfaceUtil.toFileSize(new File(path));
                            Log.e(TAG, "照片大小:" + size);
                            startActivity(ShareActivity.newIntent(ImageComPressionActivity.this, path, "照片大小:" + size, ExifInterfaceUtil.getImagedata(path)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}