package com.qmui.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.util.ToastUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tcking.github.com.giraffeplayer.GiraffePlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Author : Z-JC
 * Date : 2019/11/21
 * Description :
 */
public class VideoActivity extends BaseActivity {

    @BindView(R.id.topbar)
    public QMUITopBar topbar;
    @BindView(R.id.btn_play)
    public Button btnPlay;

    private GiraffePlayer player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("视频播放");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });
    }

    private void startVideo() {
        String finalVideo = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        player = new GiraffePlayer(this);
        player.play(finalVideo);
        player.setScaleType(GiraffePlayer.SCALETYPE_FITXY);
        player.onComplete(new Runnable() {
            @Override
            public void run() {
                //callback when video is finish
                player.start();
            }
        }).onInfo(new GiraffePlayer.OnInfoListener() {
            @Override
            public void onInfo(int what, int extra) {
                switch (what) {
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
                        //do something when buffering start
                        break;
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                        //do something when buffering end
                        break;
                    case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH:
                        //download speed
                        break;
                    case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                        //do something when video rendering
                        break;
                }
            }
        }).onError(new GiraffePlayer.OnErrorListener() {
            @Override
            public void onError(int what, int extra) {
                ToastUtil.showShortToast(VideoActivity.this, "video play error");
                if (player != null) {
                    player.onPause();
                }
            }
        });
    }

    @OnClick(R.id.btn_play)
    public void onClick() {
        startVideo();
    }
}