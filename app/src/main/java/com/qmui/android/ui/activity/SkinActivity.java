package com.qmui.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.ui.skin.FaceBeauty;
import com.qmui.android.ui.skin.GlobalDefinitions;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Z-JC
 * Date : 2020/4/27
 * Description :
 */
public class SkinActivity extends BaseActivity {

    public static final String FILE_PATH = "file_path";
    @BindView(R.id.topbar)
    public QMUITopBar topbar;
    @BindView(R.id.imgView)
    public ImageView imgView;
    @BindView(R.id.seekbar_1)
    public SeekBar seekbar1;
    @BindView(R.id.seekbar_2)
    public SeekBar seekbar2;

    public String filePath = "";
    private FaceBeauty faceBeauty;

    public static void startAct(Activity a, String path) {
        Intent intent = new Intent(a, SkinActivity.class);
        intent.putExtra(FILE_PATH, path);
        a.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        filePath = getIntent().getStringExtra(FILE_PATH);
        faceBeauty = new FaceBeauty(this, filePath);
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("人像美颜");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });
        imgView.setImageBitmap(faceBeauty.getDisplayImage());

        /**
         * 磨皮
         */
        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imgView.setImageBitmap(null);
                imgView.setImageBitmap(faceBeauty.BFSoftskin(GlobalDefinitions.softRatio, Math.abs(seekBar.getProgress())));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /**
         * 美白
         */
        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imgView.setImageBitmap(null);
                //softskin
                imgView.setImageBitmap(faceBeauty.BFSoftskin(Math.abs(seekBar.getProgress()), GlobalDefinitions.whiteRatio));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}