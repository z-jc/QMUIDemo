package com.qmui.android.ui.activity;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.ui.glide.GlideEngine;
import com.qmui.android.util.ExifInterfaceUtil;
import com.qmui.android.util.ToastUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.qmui.android.util.Constant.getPath;

public class ImageComPressionActivity extends BaseActivity {

    @BindView(R.id.btn_select_image)
    public QMUIRoundButton btnSelectImage;
    @BindView(R.id.topbar)
    public QMUITopBar topbar;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_image_compression;
    }

    @Override
    public void initView() {
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("照片采集");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });
    }

    @OnClick(R.id.btn_select_image)
    public void onClick() {
        EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                .setFileProviderAuthority("com.qmui.android.fileprovider")
                .start(new SelectCallback() {
                    @Override
                    public void onResult(ArrayList<Photo> photos, final ArrayList<String> paths, boolean isOriginal) {
                        Luban.with(ImageComPressionActivity.this)
                                .load(paths.get(0))
                                .ignoreBy(75)
                                .setTargetDir(getPath())
                                .filter(new CompressionPredicate() {
                                    @Override
                                    public boolean apply(String path) {
                                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                                    }
                                })
                                .setCompressListener(new OnCompressListener() {
                                    @Override
                                    public void onStart() {
                                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                        ImageComPressionActivity.this.showLoading();
                                    }

                                    @Override
                                    public void onSuccess(File file) {
                                        // TODO 压缩成功后调用，返回压缩后的图片文件
                                        ImageComPressionActivity.this.dismissLoading();
                                        ToastUtil.showShortToast(ImageComPressionActivity.this, "压缩成功");
                                        String path = file.getAbsolutePath();
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

                                    @Override
                                    public void onError(Throwable e) {
                                        // TODO 当压缩过程出现问题时调用
                                        ImageComPressionActivity.this.dismissLoading();
                                        ToastUtil.showShortToast(ImageComPressionActivity.this, "压缩失败");
                                    }
                                }).launch();

                    }
                });
    }
}